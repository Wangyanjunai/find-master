package com.potato369.find.dynamic.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;
import java.util.List;

@Configuration
@SuppressWarnings({"unchecked", "rawtypes"})
public class MvcConfig extends WebMvcConfigurationSupport {

	@Bean
	public HttpMessageConverters fastJsonHttpMessageConverters() {
		FastJsonHttpMessageConverter fastConvert = new FastJsonHttpMessageConverter();
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat, SerializerFeature.WriteNullStringAsEmpty,
				SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullListAsEmpty,
				SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat);
		fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
		// 处理中文乱码问题
		List fastMediaTypes = new ArrayList<>();
		fastMediaTypes.add("application/json;charset=utf-8");
		fastMediaTypes.add("application/xml;charset=utf-8");
		fastMediaTypes.add("multipart/form-data;charset=utf-8");
		fastConvert.setSupportedMediaTypes(fastMediaTypes);
		fastConvert.setFastJsonConfig(fastJsonConfig);
		return new HttpMessageConverters(fastConvert);
	}

	// 解决跨域问题
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**") // **代表所有路径
				.allowedOrigins("*") // allowOrigin指可以通过的ip，*代表所有，可以使用指定的ip，多个的话可以用逗号分隔，默认为*
				.allowedMethods("GET", "POST", "HEAD", "PUT", "DELETE") // 指请求方式 默认为*
				.allowCredentials(false) // 支持证书，默认为true
				.maxAge(3600) // 最大过期时间，默认为-1
				.allowedHeaders("*");
	}
}
