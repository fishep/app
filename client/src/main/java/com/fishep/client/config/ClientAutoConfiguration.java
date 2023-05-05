package com.fishep.client.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScans(@ComponentScan("com.fishep.client"))
public class ClientAutoConfiguration {
}
