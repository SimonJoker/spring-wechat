package com.meiben.wechat.controller;

import com.meiben.wechat.logic.WxSettingLogic;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by joker on 2016/5/19.
 * 微信菜单设置已经配置信息获取接口
 */
@Controller
@RequestMapping("/menu")
public class WxMenuController {

    @Autowired
    WxSettingLogic wxSettingLogic;


    /**
     * @param body post content 主体
     * @return
     */
    @RequestMapping(value = "/createmenu", method = RequestMethod.POST)
    @ResponseBody
    public String createMenu(@RequestBody String body) {
        if (body != null && !"".equals(body)) {
            JSONObject menu = JSONObject.fromObject(body);
            return wxSettingLogic.wxMenuSetting(menu).toString();
        }
        return wxSettingLogic.getErrorCallback("createmenu").toString();
    }

    /**
     * 获取当前公众号自定义菜单信息
     * @return
     */
    @RequestMapping(value = "/selfmenuinfo", method = RequestMethod.GET)
    @ResponseBody
    public String getMenuInfo() {
//        JSONObject cb = new JSONObject();
//        cb.element("api", "test").element("result", 200);
//        return cb.toString();
        return wxSettingLogic.wxGetCurrentSefMenu().toString();
//        return wxSettingLogic.getErrorCallback("createmenu").toString();
    }

}
