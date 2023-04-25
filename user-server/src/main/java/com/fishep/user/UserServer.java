package com.fishep.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@ComponentScans(@ComponentScan("com.fishep.server"))
public class UserServer {
    public static void main(String[] args) {
        SpringApplication.run(UserServer.class);
    }
}
