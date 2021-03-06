package com.meiben.wechat.logic;

import com.meiben.wechat.common.api.WxMenuSetting;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;

/**
 * Created by joker on 2016/6/30.
 */
public class WxSettingLogic {

    public WxSettingLogic() {
    }

    /**
     * @param menu 菜单设置json
     * @return json格式的设置结果
     */
    public JSONObject wxMenuSetting(JSONObject menu){
        JSONObject callback = new JSONObject();
        callback.put("api", "createmenu");
        callback.put("result", 302);
        System.out.println("menu--->"+menu);
        try {
            int result =WxMenuSetting.createMenu(menu.toString());
            if (result == 0){
                callback.element("result", 200);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return callback;
    }

    /**
     * 获取当前公众号配置信息接口
     * @return {JsonObject}
     */
    public JSONObject wxGetCurrentSefMenu(){
        JSONObject callback = new JSONObject();
        callback.put("api", "selfmenuinfo");
        callback.put("result", 302);

        try {
            JSONObject data = WxMenuSetting.getCurrentSelfMenuInfo();
            callback.element("result", 200);
            callback.element("data", data);
        } catch (IOException e) {
            System.out.println("failed!get current menu info --:");
            e.printStackTrace();
        }
        return callback;
    }

    /**
     * @param api 接口api
     * @return
     */
    public JSONObject getErrorCallback(String api){
        JSONObject callback = new JSONObject();
        callback.put("api", api);
        callback.put("result", 304); //body is null
        return callback;
    }
}
