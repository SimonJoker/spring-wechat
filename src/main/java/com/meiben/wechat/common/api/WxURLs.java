package com.meiben.wechat.common.api;

/**
 * Created by joker on 2016/7/1.
 */
public class WxURLs {
    /*ACCESS TOKEN URLS*/
    public static final String ACCESS_TOKEN_URL =
            "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    /*用户基本信息接口*/
//    public static final String USER_BASE_INFO_URL
//            = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    public static final String USER_BASE_INFO_URL
            = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=";
}
