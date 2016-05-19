package com.meiben.wechat.InitWechat;

import com.meiben.wechat.domain.menu.Button;
import com.meiben.wechat.domain.menu.ClickButton;
import com.meiben.wechat.domain.menu.Menu;
import com.meiben.wechat.domain.menu.ViewButton;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;

import static com.meiben.wechat.utils.HttpUtil.doPostStr;

/**
 * Created by joker on 2016/5/19.
 */
public class InitWechatParm {

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
     * 初始化菜单
     * @return
     */
    public static Menu initMenu(){
        Menu menu = new Menu();
        ClickButton button11 = new ClickButton();
        button11.setName("点击菜单");
        button11.setType("click");
        button11.setKey("11");

        ViewButton button21 = new ViewButton();
        button21.setName("网页菜单");
        button21.setType("view");
        button21.setUrl("http://wap.koudaitong.com/v2/feature/n5ro82j");

        ClickButton button31 = new ClickButton();
        button31.setName("扫码菜单");
        button31.setType("scancode_push");
        button31.setKey("31");

        ClickButton button32 = new ClickButton();
        button32.setName("位置选择器菜单");
        button32.setType("location_select");
        button32.setKey("32");

        Button button = new Button();
        button.setName("客服菜单");
        button.setSub_button(new Button[]{button31,button32});

        menu.setButton(new Button[]{button11,button21,button});
        return menu;
    }

    /**
     * 创建菜单
     * @param token
     * @param menu
     * @return
     * @throws ParseException
     * @throws IOException
     */
    public static int createMenu(String token,String menu) throws ParseException, IOException{
        int result = 0;
        String url = CREATE_MENU_URL.replace("ACCESS_TOKEN", token);
        JSONObject jsonObject = doPostStr(url, menu);
        if(jsonObject != null){
            result = jsonObject.getInt("errcode");
        }
        return result;
    }
}
