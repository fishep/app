package com.fishep.erp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

/**
 * @Author fly.fei
 * @Date 2023/7/1 9:48
 * @Desc
 **/
//@Configuration
public class LocaleConfigure {

//    @Value("${spring.web.locale:#{null}}")
    private String locale;

//    @TODO 期望通过自动装配，能把国际化的逻辑剥离出 GlobalFilter
//    @Bean
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        if (locale != null && locale.equals("zh_CN")) {
            localeResolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        } else {
            localeResolver.setDefaultLocale(Locale.US);
        }

        return localeResolver;
    }

}
