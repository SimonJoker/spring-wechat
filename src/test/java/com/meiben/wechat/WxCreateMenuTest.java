package com.meiben.wechat;

import com.meiben.wechat.common.bean.Button;
import com.meiben.wechat.common.bean.ClickButton;
import com.meiben.wechat.common.bean.Menu;
import com.meiben.wechat.common.bean.ViewButton;
import net.sf.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;

/**
 * Created by joker on 2016/6/29.
 */
public class WxCreateMenuTest {

    @Test
    public void testCreateMenu(){
        Menu menu = new Menu();
        ClickButton button11 = new ClickButton();
        button11.setName("点击菜单");
        button11.setType("click");
        button11.setKey("11");

        ViewButton button21 = new ViewButton();
        button21.setName("网页菜单");
        button21.setType("view");
        button21.setUrl("http://7a5a97e3.ngrok.io/x5/UI2/v_/chaoshi/index.w");

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
//        AccessToken token =
//                AccessTokenUtil.getAccessToken(this.getClass().getResource("/").getFile().toString());
//        curl -l -H "Content-type: application/json" -X POST -d '{"button":[{"key":"11","name":"点击菜单","sub_button":[],"type":"click"},{"name":"网页菜单","sub_button":[],"type":"view","url":"http://7a5a97e3.ngrok.io/x5/UI2/v_/chaoshi/index.w"},{"name":"客服菜单","sub_button":[{"key":"31","name":"扫码菜单","sub_button":[],"type":"scancode_push"},{"key":"32","name":"位置选择器菜单","sub_button":[],"type":"location_select"}],"type":""}]}'  http://localhost:8080/createmenu

        String menuStr = JSONObject.fromObject(menu).toString();
        System.out.println("menuStr-->"+menuStr);
//        int result = WxMenuSetting.createMenu(menuStr);
//        System.out.println("result --->"+result);
    }
}
