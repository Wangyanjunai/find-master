package com.potato369.find.portal.config;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.potato369.find.portal.config.bean.FeignSpringFormEncoder;

import feign.codec.Encoder;

@Configuration
public class FeignMultipartSupportConfig {

	@Autowired
	private ObjectFactory<HttpMessageConverters> messageConverters;

	@Bean
	public Encoder feignFormEncoder() {
		return new FeignSpringFormEncoder(new SpringEncoder(messageConverters));
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
	}
	
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
		commonsMultipartResolver.setMaxUploadSize(10000000);
		return commonsMultipartResolver;
	}
}
