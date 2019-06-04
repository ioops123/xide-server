package com.xide.session;

import com.google.common.collect.ImmutableMap;
import com.xide.session.dto.Session;
import com.xide.utils.IdGen;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;

@Data
public class SessionUtil {

    private String sessionPrefix;//redis缓存的登录令牌统一前缀标识

    private String sessionUserPrefix;//redis缓存的登录人员ID统一前缀标识

    private int sessionExpireSecond;//session过期时间，单位秒

    private JedisPool jedisPool;//redis连接池

    private static final int accessTokenHistorySize = 2;//登录人员ID缓存登录令牌最大数量

    private static final String guestKeyAndSecretTag = "GUEST";//游客模式下登录令牌和加密标识

    public SessionUtil(){
    }

    public SessionUtil(String sessionPrefix, String sessionUserPrefix, int sessionExpireSecond, JedisPool jedisPool){
        this.sessionPrefix = sessionPrefix;
        this.sessionUserPrefix = sessionUserPrefix;
        this.sessionExpireSecond = sessionExpireSecond;
        this.jedisPool = jedisPool;
    }

    /**
     * 生成redis中登录令牌的完整值(前缀标识+登录令牌)
     * @param accessToken
     * @return
     */
    private String generateAccessTokenKey(String accessToken) {
        return new StringBuffer().append(sessionPrefix).append(accessToken).toString();
    }

    /**
     * 生成redis中登录人员ID的完整值(前缀标识+登录人员ID)
     * @param userID
     * @return
     */
    private String generateUserIDKey(String userID) {
        return new StringBuffer().append(sessionUserPrefix).append(userID).toString();
    }

    /**
     * 从redis中获取登录信息
     * @param accessToken 登录令牌
     * @return
     */
    public Session getSession(String accessToken) {
        if(guestKeyAndSecretTag.equals(accessToken)){//游客模式不存放redis
            return createGuest();
        }

        try {
            Jedis jedis = getJedis();
            String accessTokenKey = generateAccessTokenKey(accessToken);
            Map<String, String> objectMap = getAccessTokenMap(jedis, accessTokenKey);

            if (objectMap != null && objectMap.size() > 0) {
                Session session = new Session(accessToken, objectMap);
                if(session != null && !session.getIsValid()){//令牌失效
                    delKeyInRedis(jedis, accessTokenKey);//删除缓存
                }else{
                    setAccessTokenExpire(jedis, accessTokenKey);//重新设置缓存过期
                }
                return session;
            }
        }catch (Exception e){
        }
        return null;
    }

    /**
     * 在redis中缓存登录信息，并返回登录令牌
     * @param userId 登录人员ID
     * @return
     */
    public Session createSession(String userId) {
        String accessToken = accessToken();
        String accessTokenKey = generateAccessTokenKey(accessToken);
        String userIDKey = generateUserIDKey(userId);

        ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
        if(StringUtils.isNotEmpty(userId)) {
            builder.put(Session.Key.id.name(), userId);
        }
        builder.put(Session.Key.secret.name(), IdGen.getUUID());
        builder.put(Session.Key.valid.name(), "1");
        Map<String, String> sessionObjectMap = builder.build();

        Jedis jedis = getJedis();
        createAccessTokenKey(jedis, accessTokenKey, sessionObjectMap);
        if(StringUtils.isNotEmpty(userId)){
            dealOldAccessTokenKey(jedis, userIDKey, accessTokenKey);
        }
        return new Session(accessToken, sessionObjectMap);
    }

    /**
     * redis中保存accessToken登录令牌及登录相关信息
     * @param jedis
     * @param accessTokenKey 登录令牌key
     * @param values 登录相关信息
     * @return
     */
    private void createAccessTokenKey(Jedis jedis, String accessTokenKey, Map<String, String> values) {
        jedis.hmset(accessTokenKey, values);
        jedis.expire(accessTokenKey, sessionExpireSecond);
    }

    /**
     * 人员登录成功后，有其他人用同一账号再次登录，则失效之前人员登录的缓存信息，之前失效的缓存过多时同时删除无效的缓存
     * @param jedis
     * @param userIDKey 登录人员ID key
     * @param newAccessTokenKey
     */
    private void dealOldAccessTokenKey(Jedis jedis, String userIDKey, String newAccessTokenKey) {
        int accessTokenKeyListSize = jedis.llen(userIDKey).intValue();

        //删除掉最早的失效的登录令牌缓存
        if (accessTokenKeyListSize >= accessTokenHistorySize) {
            String needDelOldAccessTokenKey = jedis.rpop(userIDKey);
            delKeyInRedis(jedis, needDelOldAccessTokenKey);
        }

        //将上次的登录令牌置为无效
        String lastAccessTokenKey = jedis.lindex(userIDKey, 0);
        if (lastAccessTokenKey != null) {
            jedis.hset(lastAccessTokenKey, Session.Key.valid.name(), "0");
        }

        //将最新的登录令牌保存到redis的登录人员ID key值中
        jedis.lpush(userIDKey, newAccessTokenKey);
        jedis.expire(userIDKey, sessionExpireSecond);
    }

    /**
     * 生成accessToken登录令牌
     * @return
     */
    private String accessToken() {
        return IdGen.getUUID();
    }

    /**
     * 获取Jedis连接
     * @return
     */
    public Jedis getJedis(){
        return jedisPool.getResource();
    }

    /**
     * 获取accessToken（登录令牌）对应的登录信息集合
     * @param accessTokenKey
     * @return
     */
    public Map<String, String> getAccessTokenMap(Jedis jedis, String accessTokenKey) {
        return jedis.hgetAll(accessTokenKey);
    }

    /**
     * 设置该accessToken的过期时间
     * @param accessTokenKey
     * @return
     */
    public Map<String, String> setAccessTokenExpire(Jedis jedis, String accessTokenKey) {
        if(jedis.hgetAll(accessTokenKey) != null){
            jedis.expire(accessTokenKey, sessionExpireSecond);
        }
        return null;
    }

    /**
     * 删除redis中的值
     * @param key
     */
    public void delKeyInRedis(Jedis jedis, String key) {
       jedis.del(key);
    }

    /**
     * 删除redis中的accessTokenKey值
     * @param accessTokenKey
     */
    public void delAccessTokenInRedis(Jedis jedis, String accessTokenKey) {
        jedis.del(generateAccessTokenKey(accessTokenKey));
    }

    /**
     * 游客模式
     * @return
     */
    public Session createGuest() {
        Session session = new Session();
        session.setAccessToken(guestKeyAndSecretTag);
        session.setSecret(guestKeyAndSecretTag);
        session.setIsValid(true);
        return session;
    }
}
