package com.meiben.wechat.common.utils;

import net.sf.json.JSONObject;

/**
 * Created by joker on 2016/7/4.
 */
public class WxCallbackUtil {

    /**
     * @param callback
     * @return 请求微信信息是否成功
     */
    public static boolean isSuccess(JSONObject callback){
        if (callback.containsKey("errcode")){
            return false;
        }
        return true;
    }
}
