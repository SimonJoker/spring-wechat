package com.meiben.wechat.handler;

import com.meiben.wechat.common.api.IHandler;

import java.util.Map;

/**
 * Created by joker on 2016/6/30.
 */
public class WxTextMsgHandler implements IHandler{
    @Override
    public String handlMessage(Map<String, String> request) {
        System.out.println("WxTextMsgHandler requext -->"+request);
        return "test msg";
    }
}
