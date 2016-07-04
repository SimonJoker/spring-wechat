package com.meiben.wechat.controller;

import com.meiben.wechat.WxSetting.WxMenuSetting;
import com.meiben.wechat.logic.WxMsgLogic;
import com.meiben.wechat.logic.WxSettingLogic;
import com.meiben.wechat.utils.InitWechatUtil;
import com.meiben.wechat.utils.XmlUtil;
import net.sf.json.JSONObject;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by joker on 2016/5/19.
 */
@Controller
@RequestMapping("/")
public class InitController {

    @Autowired
    WxSettingLogic wxSettingLogic;

    @Autowired
    WxMsgLogic wxMsgLogic;

    /**
     * @param signature
     * @param echostr
     * @param timestamp
     * @param nonce
     * @return 微信初始请求必须的验证
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String initWeChatRequest(@RequestParam String signature, @RequestParam String echostr
            , @RequestParam String timestamp, @RequestParam String nonce) {
        if (InitWechatUtil.checkSignatrue(signature, timestamp, nonce)) {
            return echostr;
        }
        return "error";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String msgAction(@RequestBody String body) {
        System.out.println("body --->"+body);
        String callback = null;
        Map<String, String> map = new HashMap<>();
        if (body != null && !"".equals(body)){
            try {
                map = XmlUtil.xmlToMap(body);
                callback = wxMsgLogic.getWxMpMessageRouter().excute(map);
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
        System.out.println("callback ---->"+callback);
        return "error";
    }


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

}
