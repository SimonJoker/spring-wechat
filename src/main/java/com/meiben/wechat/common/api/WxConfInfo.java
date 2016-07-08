package com.meiben.wechat.common.api;

import lombok.Data;

/**
 * Created by joker on 2016/7/6.
 */
@Data
public class WxConfInfo {
    private String appid ;
    private String appsecret;
    private String token;
    private String encodingAESKey;
}
