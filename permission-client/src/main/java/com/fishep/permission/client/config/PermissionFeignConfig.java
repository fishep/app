package com.fishep.permission.client.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients("com.fishep.permission.client.feign")
public class PermissionFeignConfig {
}
