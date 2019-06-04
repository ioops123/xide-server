package com.xide.session;

import redis.clients.jedis.JedisPool;

/**
 * 习得APP用户登录session公用方法
 */
public class AppXideSessionUtil extends SessionUtil {

    /**
     * redis缓存的登录令牌统一前缀标识
     */
    public static final String sessionPrefix = "sc:session:app:xide:accessToken:";

    /**
     * redis缓存的登录人员ID统一前缀标识
     */
    public static final String userSessionPrefix = "sc:session:app:xide:userID:";

    /**
     * session过期时间，单位秒
     */
    public static final int sessionExpireSecond = 10 * 24 * 60 * 60;

    public AppXideSessionUtil(JedisPool jedisPool){
        super(sessionPrefix, userSessionPrefix, sessionExpireSecond, jedisPool);
    }
}
