package com.xide.config;

import com.xide.session.AppXideSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

@Configuration
public class RedisConfig {

    @Autowired
    private Environment env;

    @Bean
    public JedisPool redisConnectionFactory() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(1000);
        config.setMaxIdle(1000);
        config.setMinIdle(1);
        config.setTestOnBorrow(true);
        config.setTestWhileIdle(true);
        config.setTestOnReturn(false);
        config.setBlockWhenExhausted(true);
        config.setJmxEnabled(true);
        config.setJmxNamePrefix("jedis-pool");
        config.setNumTestsPerEvictionRun(100);
        config.setTimeBetweenEvictionRunsMillis(60000);
        config.setMinEvictableIdleTimeMillis(300000);
        config.setEvictionPolicyClassName("org.apache.commons.pool2.impl.DefaultEvictionPolicy");
        config.setTimeBetweenEvictionRunsMillis(600 * 1000);

        String host = env.getProperty("redis.connection.master.url");
        Integer port = Integer.valueOf(env.getProperty("redis.connection.master.port"));
        String password = env.getProperty("redis.connection.master.password");
        String db = env.getProperty("redis.connection.master.db");

        if(StringUtils.isEmpty(db)){
            db = "0";
        }
        if (StringUtils.isEmpty(password)) {
            return new JedisPool(config, host, port, Integer.valueOf(db));
        } else {
            return new JedisPool(config, host, port, Protocol.DEFAULT_TIMEOUT, password, Integer.valueOf(db));
        }
    }

    @Bean(name = "appXideSessionUtil")
    public AppXideSessionUtil appWjkSessionUtil(JedisPool jedisPool){
        return new AppXideSessionUtil(jedisPool);
    }
}
