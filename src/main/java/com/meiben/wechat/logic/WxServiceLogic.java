package com.meiben.wechat.logic;

import com.meiben.wechat.common.api.WxConfInfo;
import com.meiben.wechat.common.api.WxURLs;
import com.meiben.wechat.common.utils.HttpUtil;
import com.meiben.wechat.dao.WxUserDao;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by joker on 2016/7/6.
 * 处理weixin公众号端页面数据请求逻辑
 */
public class WxServiceLogic {

    @Autowired
    WxConfInfo wxConfInfo;

    @Autowired
    WxUserDao wxUserDao;


    public JSONObject getWxUserInfo(String code) {
        JSONObject uInfoCallback = null;
        if (code != null && !"".equals(code)) {
            try {
                Map<String, String> token =
                        new HashMap<String, String>();
                if (wxUserDao.isExist(code)) {
                    //code未失效,则由数据库中读出access_token进行数据请求
                    token = wxUserDao.getCodeToke(code);
                } else {
                    //code 5分钟失效，则重新请求access_token，并保存缓存
                    String tokenURL = null;
                    tokenURL = WxURLs.CODE_ACCESS_TOKEN_URL
                            + URLEncoder.encode(wxConfInfo.getAppid(), "utf-8")
                            + "&secret="
                            + URLEncoder.encode(wxConfInfo.getAppsecret(), "utf-8")
                            + "&code="
                            + URLEncoder.encode(code, "utf-8")
                            + "&grant_type=authorization_code";

                    JSONObject tokenCallback = HttpUtil.doGetStr(tokenURL);
                    if (tokenCallback != null && !tokenCallback.containsKey("errcode")) {
                        tokenCallback.replace("expires_in"
                                , tokenCallback.get("expires_in").toString());
                        token =
                                (Map<String, String>) JSONObject.toBean(tokenCallback, Map.class);
                        System.out.println("token map---:" + token.toString());
                        wxUserDao.saveCode(code, token);
                    }
                }
                String uInfoUrl = WxURLs.AUTH_USER_INFO_URL
                        + URLEncoder.encode(token.get("access_token"), "utf-8")
                        + "&openid="
                        + URLEncoder.encode(token.get("openid"), "utf-8")
                        + "&lang=zh_CN";

                uInfoCallback = HttpUtil.doGetStr(uInfoUrl);
                System.out.println("uInfoCallback---:" + uInfoCallback);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return uInfoCallback;
    }
}
