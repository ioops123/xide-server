package com.xide.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 重放攻击检查
 */
@Component
public class ReplayAttackDefender {

    @Autowired
    private JedisPool jedisPool;

    /**
     * redis缓存的重放攻击请求ID前缀标识
     */
    private static final String prefix = "sc:req:replayattack:";

    public boolean check(String requestId) {
        try (Jedis jedis = jedisPool.getResource()) {
            String requestIdKey = new StringBuffer().append(prefix).append(requestId).toString();
            if (jedis.exists(requestIdKey)) {
                jedis.setex(requestIdKey, 600, "");
                return false;
            } else {
                jedis.setex(requestIdKey, 180, "");
                return true;
            }
        }
    }

}
