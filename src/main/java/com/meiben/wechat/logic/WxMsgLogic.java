package com.meiben.wechat.logic;

import com.meiben.wechat.common.api.WxConsts;
import com.meiben.wechat.common.api.WxMpMessageRouter;
import com.meiben.wechat.handler.WxSubscribeHandler;
import com.meiben.wechat.handler.WxEventClickKeyHandler;
import com.meiben.wechat.handler.WxTextMsgContentHandler;
import com.meiben.wechat.handler.WxTextMsgHandler;

/**
 * Created by joker on 2016/6/30.
 * 处理微信消息
 */
public class WxMsgLogic {


    private WxMpMessageRouter wxMpMessageRouter;

    public WxMsgLogic(WxMpMessageRouter wxMpMessageRouter) {
        this.wxMpMessageRouter = wxMpMessageRouter;
        this.wxMpMessageRouter
                .rule()
                .type(WxConsts.XML_MSG_TEXT).content("ab").handler(new WxTextMsgContentHandler()).end()
                .rule()
                .type(WxConsts.XML_MSG_EVENT).event(WxConsts.EVT_CLICK).eventKey("11")
                .handler(new WxEventClickKeyHandler()).end()
                .rule()
                .type(WxConsts.XML_MSG_EVENT).event(WxConsts.EVT_SUBSCRIBE).handler(new WxSubscribeHandler()).end()
                .rule()
                .type(WxConsts.XML_MSG_TEXT).handler(new WxTextMsgHandler()).end();


        System.out.println("size--:"+this.wxMpMessageRouter.getRules().size());
    }

    public WxMpMessageRouter getWxMpMessageRouter() {
        return wxMpMessageRouter;
    }

    public void setWxMpMessageRouter(WxMpMessageRouter wxMpMessageRouter) {
        this.wxMpMessageRouter = wxMpMessageRouter;
    }
}
