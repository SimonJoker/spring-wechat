package com.meiben.wechat.dao;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;

/**
 * Created by joker on 2016/7/6.
 */
public class WxUserDao {

    private final static String WX_CODE_KEY = "wx_code.";

    private Jedis  jedis;

    public WxUserDao(String pass, int dbIndex, JedisPool jedisPool) {
        System.out.println("pool --->"+jedisPool);
        jedis = jedisPool.getResource();
        jedis.auth(pass);
        jedis.select(dbIndex);
    }

    /**
     * @param code 与特定前缀组成key
     * @param map hashmap中保存的值
     */
    public void saveCode(String code, Map<String, String> map){
        String key = WX_CODE_KEY+code;
        jedis.hmset(key, map);
        jedis.expire(key, 5*60+5); // 设置每条记录5（5s偏差）分钟后自动过期
    }

    /**
     * @param code
     * @return 判断是否存在code的记录
     */
    public boolean isExist(String code){
        String key = WX_CODE_KEY+code;
        return jedis.exists(key);
    }

    /**
     * @param code
     * @return 返回该键值下hashmap的所有值
     */
    public Map<String, String> getCodeToke(String code){
        String key = WX_CODE_KEY+code;
        return jedis.hgetAll(key);

    }
}
