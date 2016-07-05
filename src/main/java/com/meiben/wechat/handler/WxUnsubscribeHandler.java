package com.meiben.wechat.handler;

import com.meiben.wechat.common.api.IHandler;
import com.meiben.wechat.common.api.WxErrCodes;
import com.meiben.wechat.common.api.WxURLs;
import com.meiben.wechat.common.bean.AccessToken;
import com.meiben.wechat.common.utils.AccessTokenUtil;
import com.meiben.wechat.common.utils.HttpUtil;
import com.meiben.wechat.common.utils.UidUtils;
import com.meiben.wechat.common.utils.WxCallbackUtil;
import com.meiben.wechat.entity.User;
import com.meiben.wechat.mapper.UserMapper;
import net.sf.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by joker on 2016/6/30.
 */
public class WxUnsubscribeHandler implements IHandler {
    private UserMapper userMapper = null;

    public WxUnsubscribeHandler() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:db-config/mysql-jdbc-conf.xml");
        this.userMapper = applicationContext.getBean("userMapper", UserMapper.class);
    }

    /**
     * @param request 请求的参数
     * @return
     */
    @Override
    public String handlMessage(Map<String, String> request) {
        String openid = request.get("FromUserName");
        try {
            this.userMapper.delete(openid);
            System.out.println("delete user: openid=" + openid + " success!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
