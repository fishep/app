package com.fishep.oms.interfaces.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class OMSWebConfig implements WebMvcConfigurer {

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix("/api/oms", clazz -> clazz.isAnnotationPresent(RestController.class));
        WebMvcConfigurer.super.configurePathMatch(configurer);
    }
}
