package com.meiben.wechat.controller;

import com.meiben.wechat.logic.WxMsgListener;
import com.meiben.wechat.logic.WxSettingLogic;
import com.meiben.wechat.common.utils.*;
import com.meiben.wechat.mapper.UserMapper;
import net.sf.json.JSONObject;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    WxMsgListener wxMsgListener;

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
        System.out.println("request---: "+signature+"\n"+echostr+"\n"+timestamp+"\n"+nonce);
        if (InitWechatUtil.checkSignatrue(signature, timestamp, nonce)) {
            return echostr;
        }

        return "error";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String msgAction(@RequestBody String body) {
        String callback = null;
        Map<String, String> map = new HashMap<String, String>();
        if (body != null && !"".equals(body)){
            try {
                map = XmlUtil.xmlToMap(body);
                callback = wxMsgListener.getWxMpMessageRouter().excute(map);
                return callback;
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
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
