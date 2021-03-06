package com.potato369.find.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringCloudApplication
@EnableFeignClients
public class PortalServiceApplication {	
    public static void main(String[] args) {
        SpringApplication.run(PortalServiceApplication.class, args);
    }
}
