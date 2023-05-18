package com.fishep.erp.filter;

import com.fishep.common.type.Guard;
import com.fishep.user.client.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.stream.Stream;

@Component
@Slf4j
public class AuthFilter implements GlobalFilter, Ordered {

    @Value("${auth.guest.routes}")
    private String[] guestRoutes;

    @Autowired
    private AuthService authService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("AuthFilter filter request");

        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders headers = request.getHeaders();
        String uri = request.getPath().value();
        String token = headers.getFirst(HttpHeaders.AUTHORIZATION);

        // 不能在登录状态下访问的路由
        boolean match = Stream.of(guestRoutes).anyMatch(routeRegex -> uri.matches(routeRegex));
        if (match) {
            if (token != null) {
                throw new RuntimeException("token is exist, please login out!");
            }
            return chain.filter(exchange);
        }

        // 在登录的状态下，进行验证转发
        if (token == null) {
            throw new RuntimeException("token is not exist, please login!");
        }

        ServerHttpRequest.Builder builder = request.mutate();
        Long id = authService.check(Guard.ERP.toString(), token);
        builder.header("App-User-Id", String.valueOf(id));

        return chain.filter(exchange.mutate().request(builder.build()).build());
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
