package com.meiben.wechat.common.api;

import java.util.Map;

/**
 * Created by joker on 2016/6/30.
 */
public interface IHandler {

    public String handlMessage(Map<String, String> request);
}
