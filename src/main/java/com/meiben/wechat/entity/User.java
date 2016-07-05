package com.meiben.wechat.entity;
import lombok.Data;
/**
 * Created by joker on 2016/7/4.
 */

@Data
public class User implements java.io.Serializable{
    private static final long serialVersionUID = 8663915064096283715L;

    private Long uid;
    private String openid;
    private String nickname;
    private Integer sex;
    private String city;
    private String country;
    private String province;
    private String language;
    private String headimgurl;
    private Long subscribe_time;
    private String unionid;
    private String remark;
    private Integer groupid;

}
