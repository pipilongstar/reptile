package com.pipilong.config;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author pipilong
 * @createTime 2022/12/15
 * @description
 */
@Configuration
public class Config {

    @Bean
    PoolingHttpClientConnectionManager getPoolingHttpClientConnectionManager(){
        PoolingHttpClientConnectionManager pm = new PoolingHttpClientConnectionManager();
        //最大连接数
        pm.setMaxTotal(100);
        //每个主机最大连接数
        pm.setDefaultMaxPerRoute(10);
        return pm;
    }


}
