package com.meiben.wechat.controller;

import com.meiben.wechat.utils.InitWechatUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by joker on 2016/5/19.
 */
@Controller
@RequestMapping("/")
public class InitController {

    /**
     * @param signature
     * @param echostr
     * @param timestamp
     * @param nonce
     * @return  微信初始请求必须的验证
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String initWeChatRequest(@RequestParam String signature, @RequestParam String echostr
            , @RequestParam String timestamp, @RequestParam String nonce) {
        if (InitWechatUtil.checkSignatrue(signature, timestamp, nonce)){
            System.out.println("");
            return echostr;
        }
        return "error";
    }

}
