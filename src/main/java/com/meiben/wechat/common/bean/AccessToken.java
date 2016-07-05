package com.meiben.wechat.common.bean;

/**
 * Created by joker on 2016/1/13.
 */
public class AccessToken {
    private String token;
    private int expiresIn;
    private int flag = 0;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String toString(){
        return this.token + "\n" + this.expiresIn + "\n" + this.flag;
    }
}
