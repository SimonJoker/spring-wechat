package com.meiben.wechat.common.utils;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.text.ParseException;

/**
 * Created by joker on 2016/5/19.
 */
public class HttpUtil {

    /**
     * get请求方法
     * @param url
     * @return
     * @throws ParseException
     * @throws IOException
     */
    public static JSONObject doGetStr(String url) throws IOException {
        JSONObject jsonObject = null;
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet httpGet = null;
            try {
                httpGet = new HttpGet(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
            jsonObject = null;
            CloseableHttpResponse httpResponse = null;
            try {
                httpResponse = client.execute(httpGet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                HttpEntity entity = httpResponse.getEntity();
                if(entity != null){
                    String result = EntityUtils.toString(entity, "UTF-8");
                    jsonObject = JSONObject.fromObject(result);
                }
                //确认消耗掉response
                EntityUtils.consume(entity);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                httpResponse.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.apache.http.ParseException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * POST请求方法
     * @param url
     * @param outStr
     * @return
     * @throws ParseException
     * @throws IOException
     */
    public static JSONObject doPostStr(String url,String outStr) throws ParseException, IOException{
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpost = new HttpPost(url);
        JSONObject jsonObject = null;
        httpost.setEntity(new StringEntity(outStr,"UTF-8"));
        CloseableHttpResponse response = client.execute(httpost);
        String result = null;
        HttpEntity entity = response.getEntity();
        try {
            result = EntityUtils.toString(entity, "UTF-8");
            System.out.println("post result--:"+result);
            //消耗掉response
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }
        jsonObject = JSONObject.fromObject(result);
        return jsonObject;
    }
}
