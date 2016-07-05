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
public class WxSubscribeHandler implements IHandler {
    private UserMapper userMapper = null;

    public WxSubscribeHandler() {
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
        AccessToken token = AccessTokenUtil.getAccessToken();

        JSONObject usrinfo = getUserinfo(openid, token.getToken());
        if (WxCallbackUtil.isSuccess(usrinfo)){ //获取用户信息成功则插入数据库中
            Long uid = UidUtils.chUIDnext();
            usrinfo.remove("subscribe");
            usrinfo.remove("tagid_list");
            usrinfo.put("uid", uid);
            User wxUser = (User) JSONObject.toBean(usrinfo, User.class);
            try {
                this.userMapper.insert(wxUser);
                System.out.println("insert user: nickname="+wxUser.getNickname()+
                        ", openid="+wxUser.getOpenid()+" success!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private JSONObject getUserinfo(String openid, String token) {
        JSONObject usrinfo = null;
        try {
            String url = WxURLs.USER_BASE_INFO_URL
                    + URLEncoder.encode(token, "UTF-8")
                    + "&openid="
                    + URLEncoder.encode(openid, "UTF-8")
                    + "&lang=zh_CN";

            usrinfo = HttpUtil.doGetStr(url);
            //如果返回42001,则刷新accesstoken 再重新请求一次
            if (!WxCallbackUtil.isSuccess(usrinfo)
                    && WxErrCodes.ACCESS_TOKEN_INVALID.equals(usrinfo.getString("errcode"))){
                AccessToken accessToken = AccessTokenUtil.requestAccessToken();
                getUserinfo(openid, accessToken.getToken());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return usrinfo;
    }
}
