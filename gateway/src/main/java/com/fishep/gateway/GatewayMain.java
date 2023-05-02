package com.fishep.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScans(@ComponentScan("com.fishep.user.client"))
public class GatewayMain {
    public static void main(String[] args) {
        SpringApplication.run(GatewayMain.class);
    }
}
