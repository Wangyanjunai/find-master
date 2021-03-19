package com.potato369.find.portal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringCloudApplication
@EnableFeignClients(basePackages = {"com.potato369.find.portal.feign"})
@MapperScan(basePackages = {"com.potato369.find.mbg"})
public class PortalServiceApplication {	
    public static void main(String[] args) {
        SpringApplication.run(PortalServiceApplication.class, args);
    }
}
