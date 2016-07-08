package com.meiben.wechat;

import com.meiben.wechat.mapper.UserMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by joker on 2016/7/6.
 */
public class RedisTest {

    private JedisPool pool = null;

    @Before
    public void before() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:db-config/redis-conf.xml");
        pool = applicationContext.getBean("jedisPool" , JedisPool.class);

        try {
            System.out.println("jedis--:"+pool.getResource());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void redisTest(){
        System.out.println("pool--->"+pool);
        Jedis jedis = pool.getResource();
        jedis.auth("Y20160704clouds");
        System.out.println("jedis--:"+jedis);
    }
}
