package com.fishep.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@ComponentScans(@ComponentScan("com.fishep.user.client"))
public class GatewayMain {
    public static void main(String[] args) {
        SpringApplication.run(GatewayMain.class);
    }
}
