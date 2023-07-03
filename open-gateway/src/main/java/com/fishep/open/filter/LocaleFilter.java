package com.fishep.open.filter;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.i18n.SimpleLocaleContext;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Locale;

/**
 * @Author fly.fei
 * @Date 2023/7/3 14:47
 * @Desc
 **/
@Component
public class LocaleFilter implements GlobalFilter, Ordered {

    @Value("${spring.web.locale:#{null}}")
    private String locale;

    @PostConstruct
    public void setDefaultLocale() {
        if (locale != null && locale.equals("zh_CN")) {
            Locale.setDefault(Locale.SIMPLIFIED_CHINESE);
            return;
        }
        if (locale != null && locale.equals("en_US")) {
            Locale.setDefault(Locale.US);
            return;
        }
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        setLocaleContext(exchange, chain);

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    private void setLocaleContext(ServerWebExchange exchange, GatewayFilterChain chain) {

//         @TODO 需要清理LocaleContextHolder, 哪里有结束的回调清理会更好
        LocaleContextHolder.resetLocaleContext();

        String locale = exchange.getRequest().getHeaders().getFirst("Accept-Language");

        if (locale != null && locale.equals("zh-CN")) {
            LocaleContextHolder.setLocaleContext(new SimpleLocaleContext(Locale.SIMPLIFIED_CHINESE));
            return;
        }

        if (locale != null && locale.equals("en-US")) {
            LocaleContextHolder.setLocaleContext(new SimpleLocaleContext(Locale.US));
            return;
        }
    }
}
