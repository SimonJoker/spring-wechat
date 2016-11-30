package com.meiben.wechat.common.api;

import com.meiben.wechat.common.bean.AccessToken;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;

import com.meiben.wechat.common.utils.*;

/**
 * Created by joker on 2016/5/19.
 */
public class WxMenuSetting {


    /**
     * 创建菜单
     * @param menu
     * @return
     * @throws ParseException
     * @throws IOException
     */
    public static int createMenu(String menu) throws ParseException, IOException{
        AccessToken token =
                AccessTokenUtil.getAccessToken();
        int result = 0;
        String url = WxURLs.CREATE_MENU_URL.replace("ACCESS_TOKEN", token.getToken());
        JSONObject jsonObject = HttpUtil.doPostStr(url, menu);
        System.out.println("create menu--:"+jsonObject);
        if(jsonObject != null){
            result = jsonObject.getInt("errcode");
        }
        return result;
    }

    /**
     * 获取当前微信公众号自定义菜单配置信息
     * @return
     * @throws IOException
     */
    public static JSONObject getCurrentSelfMenuInfo() throws IOException {
        AccessToken token = AccessTokenUtil.getAccessToken();
        String url = WxURLs.GET_CURRENT_SELFMENU_INFO.replace("ACCESS_TOKEN", token.getToken());

        JSONObject jsonObject = HttpUtil.doGetStr(url);
        System.out.println("get current self menu info ---:"+jsonObject);
        return jsonObject;
    }
}
