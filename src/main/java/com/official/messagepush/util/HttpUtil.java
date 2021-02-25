package com.official.messagepush.util;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.hibernate.validator.internal.util.privilegedactions.GetMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author: cww
 * @date: 2021/2/24 11:42
 */
public class HttpUtil {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);

    public static String postJson(String url,String jsonStr){
        //创建httpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String result = "";
        try{
            //创建http请求
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", "application/json");
            //创建请求内容
            StringEntity entity = new StringEntity(jsonStr);
            httpPost.setEntity(entity);
            LOGGER.info("http post request:{}",httpPost.getEntity());
            response = httpClient.execute(httpPost);
            result = EntityUtils.toString(response.getEntity(),"utf-8");
            LOGGER.info("http post response:{}",result);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //关闭资源
            if(response != null){
                try {
                    response.close();
                }catch (IOException ioe){
                    ioe.printStackTrace();
                }
            }
            if(httpClient != null){
                try{
                    httpClient.close();
                }catch (IOException ioe){
                    ioe.printStackTrace();
                }
            }
        }
        return result;
    }

    public static String doGet(String url) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        try {
            HttpResponse response = httpClient.execute(get);
            String res = EntityUtils.toString(response.getEntity());
            LOGGER.info("http get response:{}",res);
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



}
