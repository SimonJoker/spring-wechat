package com.meiben.wechat.utils;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * Created by joker on 2016/1/12.
 */
public class InitWechatUtil {
    private static final String token = "Wo0LIS9CUKXGwrKr";

    /**
     * @param signature 排序加密后的字符串
     * @param timestamp 时间戳
     * @param nonce 随机码
     * @return 判断请求是否来自微信
     */
    public static boolean checkSignatrue(String signature, String timestamp, String nonce){
        String[] arr = new String []{ token, timestamp,nonce};
        Arrays.sort(arr);

        StringBuffer content = new StringBuffer();
        for (int i=0;i<arr.length;i++){
            content.append(arr[i]);
        }

        String temp = getSha1(content.toString());
        return  temp.equals(signature);
    }

    public static String getSha1(String str){
        if (str == null || str.length() == 0){
            return null;
        }
        char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i<j; i++){
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
