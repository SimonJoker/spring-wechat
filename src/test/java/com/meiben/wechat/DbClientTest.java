package com.meiben.wechat;

import com.meiben.wechat.common.utils.UidUtils;
import com.meiben.wechat.entity.User;
import com.meiben.wechat.mapper.UserMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by joker on 2016/7/4.
 */
public class DbClientTest {

    private UserMapper userMapper = null;

    @Before
    public void before() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:db-config/mysql-jdbc-conf.xml");
        userMapper = applicationContext.getBean("userMapper" , UserMapper.class);
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setUid(UidUtils.chUIDnext());
        user.setOpenid("o6_bmjrPTlm6_2sgVt7hMZOffd2l");
        user.setNickname("joker");
        user.setSex(1);
        user.setLanguage("zh_CN");
        user.setCity("上海");
        user.setProvince("上海");
        user.setCountry("中国");
        user.setHeadimgurl("http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6" +
                "iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0");
        user.setSubscribe_time(Long.valueOf(1382694957));
        user.setGroupid(0);
        user.setUnionid(" o6_bmasdasdsad6_2sgVt7hMZOPfL");

        userMapper.insert(user);
    }

    @Test
    public void testDelete() {
        userMapper.delete("o6_bmjrPTlm6_2sgVt7hMZOPfL2M");
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setUid(UidUtils.chUIDnext());
        user.setOpenid("o6_bmjrPTlm6_2sgVt7hMZOPfL2M");
        user.setNickname("Jackal");
        user.setSex(1);
        user.setLanguage("zh_CN");
        user.setCity("重庆");
        user.setProvince("重庆");
        user.setCountry("中国");
        user.setHeadimgurl("http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6" +
                "iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0");
        user.setSubscribe_time(Long.valueOf(1382694957));
        user.setGroupid(0);
        user.setUnionid(" o6_bmasdasdsad6_2sgVt7hMZOPfL");

        userMapper.updateUserAllInfo(user);

        User result = userMapper.queryByOpenid("o6_bmjrPTlm6_2sgVt7hMZOPfL2M");
        System.out.println(result);
    }

    @Test
    public void testQueryAll() {
        List<User> userList = userMapper.queryAll();
        System.out.println(userList);
    }

    @Test
    public void testQueryById() {
        User user = userMapper.queryByOpenid("o6_bmjrPTlm6_2sgVt7hMZOPfL2M");
        System.out.println(user);
    }
}
