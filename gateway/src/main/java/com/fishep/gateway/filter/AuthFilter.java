package com.fishep.gateway.filter;

import com.fishep.user.client.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.stream.Stream;

@Component
@Slf4j
public class AuthFilter implements GlobalFilter {

    @Value("${auth.guest.routes}")
    private String[] guestRoutes;

    @Autowired
    private AuthService authService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("AuthFilter filter request");

        ServerHttpRequest request = exchange.getRequest();
        String uri = request.getPath().value();
        String token = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        boolean match = Stream.of(guestRoutes).anyMatch(routeRegex -> uri.matches(routeRegex));
        if (match) {
            if (token != null) {
                throw new RuntimeException("token is exist, please login out!");
            }
            return chain.filter(exchange);
        }

        if (token == null) {
            throw new RuntimeException("token is not exist, please login!");
        }

        Long id = authService.check(token);

        ServerHttpRequest.Builder builder = request.mutate();
        builder.header("App-User-Id", String.valueOf(id));
//        builder.header("App-User-Name", "test");

        return chain.filter(exchange.mutate().request(builder.build()).build());
    }
}
