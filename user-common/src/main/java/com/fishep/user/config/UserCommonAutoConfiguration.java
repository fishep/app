package com.fishep.user.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScans(@ComponentScan("com.fishep.user"))
public class UserCommonAutoConfiguration {
}
