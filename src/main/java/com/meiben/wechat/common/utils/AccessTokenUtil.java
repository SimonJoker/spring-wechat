package com.meiben.wechat.common.utils;

import com.meiben.wechat.common.api.WxURLs;
import com.meiben.wechat.common.bean.AccessToken;
import net.sf.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by joker on 2016/5/19.
 */
public class AccessTokenUtil {
    public static final String APPID = "wx3f58d80c641ec81d";
    public static final String APPSECRET = "d4624c36b6795d1d99dcf0547af5443d";
    public static final String ACCESS_TOKEN_NAME = "accesstoken.txt";

    public static final String TOKEN_PATH
            = AccessTokenUtil.class.getClassLoader().getResource("").getFile();

    public static AccessToken requestAccessToken() {
        return requestAccessToken(TOKEN_PATH);
    }
    /**
     * 获取AccessToken
     * @return
     * @throws ParseException
     * @throws IOException
     */
    public static AccessToken requestAccessToken(String rootPath) {
        AccessToken token = new AccessToken();
        try {
            String url = WxURLs.ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
            System.out.println("a");
            JSONObject jsonObject = HttpUtil.doGetStr(url);
            if(jsonObject!=null){
                token.setToken(jsonObject.getString("access_token"));
                token.setExpiresIn(jsonObject.getInt("expires_in"));
                FileUtil.writeFile(token.toString(), rootPath+"/"+ACCESS_TOKEN_NAME);
                Timer timer = new Timer();
                //设置定时器expires_in - 5 秒
                try {
                    timer.schedule(new MyTask(rootPath)
                            , (token.getExpiresIn()-5)*1000, (token.getExpiresIn()-5)*1000);
                } catch (Exception e) {
                    setAccessTokenFlag(rootPath, token);
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return token;
    }

    /**
     * @param rootPath
     * @param token
     * 刷新token失败时标记文件中的token已过期
     */
    public static void setAccessTokenFlag(String rootPath, AccessToken token){
        token.setFlag(1);
        FileUtil.writeFile(token.toString(), rootPath+"/"+ACCESS_TOKEN_NAME);
    }

    /**
     * @return AccessToken 值
     */
    public static AccessToken getAccessToken(){
        File file = new File(TOKEN_PATH+"/"+ACCESS_TOKEN_NAME);
        if (file.exists()){
            AccessToken token = FileUtil.getTokenFromDir(file);
            if (token.getFlag() == 0){
                return token;
            }else {
                return requestAccessToken(TOKEN_PATH);
            }
        }else {
            return requestAccessToken(TOKEN_PATH);
        }
    }

    /**
     * @param rootPath 项目路径
     * @return AccessToken 值
     */
    public static AccessToken getAccessToken(String rootPath){
        File file = new File(rootPath+"/"+ACCESS_TOKEN_NAME);
        if (file.exists()){
            AccessToken token = FileUtil.getTokenFromDir(file);
            if (token.getFlag() == 0){
                return token;
            }else {
                return requestAccessToken(rootPath);
            }
        }else {
            return requestAccessToken(rootPath);
        }
    }

    /**
     * @param rootPath
     * @return 判断access token 是否过期 true: toke过期
     */
    public static boolean isActivation(String rootPath){
        int flag = 0;
        try {
            flag = Integer.valueOf(
                    FileUtil.readPointLine(rootPath+"/"+ACCESS_TOKEN_NAME, 3));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (flag == 0 ? true : false);
    }

    /**
     * 定时器计时刷新accesstoken
     */
    private static class MyTask extends TimerTask{
        String rootPath;
        public MyTask(String rootPath){
            this.rootPath = rootPath;
        }

        @Override
        public void run() {
            System.out.println("mytask");
            requestAccessToken(rootPath);
        }
    }
}
