package com.fishep.user.client.config;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClientsProperties;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

import java.util.stream.Collectors;

@Configuration
@EnableFeignClients({"com.fishep.user.client"})
public class UserFeignConfig {

    /**
     * @param converters
     * @return
     * @TODO 在webserver应用里不需要为Feign创建bean, gateway项目里必须要为Feign创建bean, 不能自己装配吗，why？
     */
    @Bean
    @ConditionalOnMissingBean
    public HttpMessageConverters httpMessageConverters(ObjectProvider<HttpMessageConverter<?>> converters) {
        return new HttpMessageConverters(converters.orderedStream().collect(Collectors.toList()));
    }

//    @Bean
//    @ConditionalOnMissingBean
//    public LoadBalancerClientFactory loadBalancerClientFactory(LoadBalancerClientsProperties properties) {
//        return new LoadBalancerClientFactory(properties) {
//            @Override
//            public AnnotationConfigApplicationContext createContext(String name) {
//                // FIXME: temporary switch classloader to use the correct one when creating the context
//                ClassLoader originalClassLoader = Thread.currentThread().getContextClassLoader();
//                Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
//                AnnotationConfigApplicationContext context = (AnnotationConfigApplicationContext) super.createContext(name);
//                Thread.currentThread().setContextClassLoader(originalClassLoader);
//                return context;
//            }
//        };
//    }

}
