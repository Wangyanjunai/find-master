package com.potato369.find.portal.config;

import com.potato369.find.portal.config.bean.FeignSpringFormEncoder;
import feign.codec.Encoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

/**
 * @author nianyu
 * 支持多文件上传配置
 * 将“FeignSpringFormEncoder”作为bean提供给框架，代替“SpringFormEncoder”的“encode()”接口
 * 参考：https://blog.csdn.net/ww_run/article/details/111400739
 */

@Configuration
public class FeignMultipartSupportConfig {

    private ObjectFactory<HttpMessageConverters> messageConverters;

    @Autowired
    public void setMessageConverters(ObjectFactory<HttpMessageConverters> messageConverters) {
        this.messageConverters = messageConverters;
    }

    @Bean
    @Primary
    @Scope("prototype")
    public Encoder multipartFormEncoder() {
        return new FeignSpringFormEncoder(new SpringEncoder(messageConverters));
    }
}


