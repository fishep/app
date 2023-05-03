package com.fishep.permission;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@EnableFeignClients
@ComponentScans(@ComponentScan({"com.fishep.server", "com.fishep.user.client"}))
public class PermissionServer {
    public static void main(String[] args) {
        SpringApplication.run(PermissionServer.class);
    }
}
