package com.potato369.find.portal.config;

import feign.codec.Encoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import com.potato369.find.portal.config.bean.FeignSpringFormEncoder;

/**
 * author：YZH
 * time: 2019/4/29 15:37
 * https://www.cnblogs.com/cq-yangzhou/p/10791008.html
 **/
public class FeignMultipartSupportConfig {
	
    @Bean
    @Primary
    @Scope("prototype")
    public Encoder multipartFormEncoder() {
        return new FeignSpringFormEncoder();
    }
}
