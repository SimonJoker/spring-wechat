package com.meiben.wechat.controller;

import com.meiben.wechat.logic.WxServiceLogic;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by joker on 2016/5/19.
 */
@Controller
@RequestMapping("/ser")
public class WxServiceController {

    @Autowired
    WxServiceLogic wxServiceLogic;


    @RequestMapping(value = "/userinfo", method = RequestMethod.GET)
    @ResponseBody
    public String wxUserInfo(@RequestParam String code) {
        System.out.println("request code---: "+code);
        JSONObject callback = wxServiceLogic.getWxUserInfo(code);
        StringBuffer result =
                new StringBuffer("result(").append(callback.toString()).append(")");

        return result.toString();
    }

}
