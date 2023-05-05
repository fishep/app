package com.fishep.permission.client.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScans(@ComponentScan("com.fishep.permission.client"))
public class PermissionClientAutoConfiguration {
}
