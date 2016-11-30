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

    public static final String UPLOAD_URL =
            "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";

    public static final String CREATE_MENU_URL =
            "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    public static final String QUERY_MENU_URL =
            "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";

    public static final String DELETE_MENU_URL =
            "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

    /*获取最定义菜单配置接口*/
    public static final String GET_CURRENT_SELFMENU_INFO =
            "https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info?access_token=ACCESS_TOKEN";

    /*通过code换取网页授权access_token*/
    public static final String CODE_ACCESS_TOKEN_URL =
            "https://api.weixin.qq.com/sns/oauth2/access_token?appid=";

    /**
     * s授权登陆，根据access_token获取用户信息的链接
     */
    public static final String AUTH_USER_INFO_URL =
            "https://api.weixin.qq.com/sns/userinfo?access_token=";
}
