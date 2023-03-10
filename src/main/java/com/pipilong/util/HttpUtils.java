package com.pipilong.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.UUID;

/**
 * @author pipilong
 * @createTime 2022/12/15
 * @description
 */
@Component
@Slf4j
public class HttpUtils {

    @Autowired
    private PoolingHttpClientConnectionManager cm;

    /**
     * 根据url下载页面数据
     * @param url
     * @return String
     */
    public String doGetHtml(String url){

        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(this.cm).build();

        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Mobile Safari/537.36");
        httpGet.setConfig(this.getConfig());

        CloseableHttpResponse response=null;
        try {
            //发get请求获取响应
            response = httpClient.execute(httpGet);

            //解析响应,返回结果
            if(response.getStatusLine().getStatusCode() == 200){
                if(response.getEntity()!=null){
                    String content = EntityUtils.toString(response.getEntity(), "utf-8");
                    return content;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "";
    }

    /**
     * 下载图片
     * @param url
     * @return String
     */
    public String doGetImage(String url){
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(this.cm).build();

        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("user-agent:", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36");
        httpGet.setConfig(this.getConfig());

        CloseableHttpResponse response=null;
        try {
            //发get请求获取响应
            response = httpClient.execute(httpGet);

            //解析响应,返回结果
            if(response.getStatusLine().getStatusCode() == 200){
                if(response.getEntity()!=null){
                    //获取图片后缀名
                    String extName=url.substring(url.lastIndexOf('.'));
                    //图片重命名
                    String picName= UUID.randomUUID().toString()+extName;
                    //下载图片
                    OutputStream os = new FileOutputStream(new File("C:\\Users\\皮皮龙\\Desktop\\java课程设计\\reptile\\src\\main\\resources\\static\\images\\"+picName));
                    response.getEntity().writeTo(os);
                    //返回图片名称
                    return picName;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "";
    }


    /**
     * 获取参数配置
     * @return RequestConfig
     */
    private RequestConfig getConfig() {
        RequestConfig config=RequestConfig.custom()
                .setConnectTimeout(1000) //创建连接的最长的时间
                .setConnectionRequestTimeout(500)
                .setSocketTimeout(10000) //数据传输的最长时间
                .build();
        return config;
    }
}


















