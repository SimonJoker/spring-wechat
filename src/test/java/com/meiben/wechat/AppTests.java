package com.meiben.wechat;

import com.meiben.wechat.InitWechat.InitWechatParm;
import com.meiben.wechat.domain.AccessToken;
import com.meiben.wechat.utils.AccessTokenUtil;
import net.sf.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.text.ParseException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class AppTests {
//    private MockMvc mockMvc;
//
//    @SuppressWarnings("SpringJavaAutowiringInspection")
//    @Autowired
//    protected WebApplicationContext wac;
//
//    @Before
//    public void setup() {
//        this.mockMvc = webAppContextSetup(this.wac).build();
//    }

    @Test
    public void simple() {
        AccessToken token =
                AccessTokenUtil.getAccessToken(this.getClass().getResource("/").getFile().toString());

        String menu = JSONObject.fromObject(InitWechatParm.initMenu()).toString();

        try {
            int result = InitWechatParm.createMenu(token.getToken(), menu);
            if (result != 0){
                System.out.println("if");
                token = AccessTokenUtil
                        .requestAccessToken(this.getClass().getResource("/").getFile().toString());
                InitWechatParm.createMenu(token.getToken(), menu);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
