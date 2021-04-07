package com.potato369.find.order.config;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2 API文档的配置
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
	
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				// 为当前包下controller生成API文档
				.apis(RequestHandlerSelectors.basePackage("com.potato369.find.order.controller"))
				// 为有@Api注解的Controller生成API文档
				.apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
				// 为有@ApiOperation注解的方法生成API文档
				.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
				.paths(PathSelectors.any()).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("觅鹿项目API文档")
				.description("find-master 是一套微服务陌生人社交系统，采用了 Spring Cloud Greenwich.SR2 & Alibaba、Spring Boot 2.1、Oauth2、MyBatis、Elasticsearch、Docker、Kubernetes等核心技术，同时提供了基于Vue的管理后台方便快速搭建系统。find-master 在电商业务的基础集成了注册中心、配置中心、监控中心、网关等系统功能。文档齐全，附带全套Spring Cloud教程。")
				.contact(new Contact("土豆互联科技（深圳）有限公司", "https://www.potato369.com", "wyj@potato369.com"))
				.version("1.0")
				.build();
	}
}
