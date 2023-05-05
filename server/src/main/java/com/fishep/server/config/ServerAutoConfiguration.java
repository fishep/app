package com.fishep.server.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScans(@ComponentScan("com.fishep.server"))
public class ServerAutoConfiguration {
}
