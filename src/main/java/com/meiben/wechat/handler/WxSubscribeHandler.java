package com.meiben.wechat.handler;

import com.meiben.wechat.common.api.IHandler;
import com.meiben.wechat.common.api.WxURLs;
import com.meiben.wechat.domain.AccessToken;
import com.meiben.wechat.utils.AccessTokenUtil;
import com.meiben.wechat.utils.HttpUtil;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.Locale;
import java.util.Map;
import java.util.stream.StreamSupport;

import static com.meiben.wechat.utils.HttpUtil.doGetStr;

/**
 * Created by joker on 2016/6/30.
 */
public class WxSubscribeHandler implements IHandler{

    @Override
    public String handlMessage(Map<String, String> request){
        String openid = request.get("FromUserName");

        AccessToken token = AccessTokenUtil.getAccessToken();

        JSONObject usrinfo = null;
        try {
            String url = null;
            url = WxURLs.USER_BASE_INFO_URL
                    +URLEncoder.encode(token.getToken(), "UTF-8")
                    +"&openid="
                    +URLEncoder.encode(openid, "UTF-8")
                    +"&lang=zh_CN";

            System.out.println("userifo url --:"+url);
            usrinfo = null;
            usrinfo = HttpUtil.doGetStr(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("userifo--:"+usrinfo.toString());
        return usrinfo.toString();
    }
}
