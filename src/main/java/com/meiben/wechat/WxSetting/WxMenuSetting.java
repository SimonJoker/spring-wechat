package com.meiben.wechat.WxSetting;

import com.meiben.wechat.domain.AccessToken;
import com.meiben.wechat.domain.menu.Button;
import com.meiben.wechat.domain.menu.ClickButton;
import com.meiben.wechat.domain.menu.Menu;
import com.meiben.wechat.domain.menu.ViewButton;
import com.meiben.wechat.utils.AccessTokenUtil;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;

import static com.meiben.wechat.utils.HttpUtil.doPostStr;

/**
 * Created by joker on 2016/5/19.
 */
public class WxMenuSetting {

    private static final String APPID = "wx3f58d80c641ec81d";
    private static final String APPSECRET = "d4624c36b6795d1d99dcf0547af5443d";

    private static final String ACCESS_TOKEN_URL =
            "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    private static final String UPLOAD_URL =
            "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";

    private static final String CREATE_MENU_URL =
            "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    private static final String QUERY_MENU_URL =
            "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";

    private static final String DELETE_MENU_URL =
            "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";


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
        String url = CREATE_MENU_URL.replace("ACCESS_TOKEN", token.getToken());
        JSONObject jsonObject = doPostStr(url, menu);
        System.out.println("create menu--:"+jsonObject);
        if(jsonObject != null){
            result = jsonObject.getInt("errcode");
        }
        return result;
    }
}
