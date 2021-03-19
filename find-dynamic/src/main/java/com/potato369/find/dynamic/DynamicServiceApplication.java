package com.potato369.find.dynamic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringCloudApplication
@MapperScan(basePackages = {"com.potato369.find.mbg"})
@EnableFeignClients(basePackages = {"com.potato369.find.dynamic.feign"})
public class DynamicServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(DynamicServiceApplication.class, args);
    }
}
