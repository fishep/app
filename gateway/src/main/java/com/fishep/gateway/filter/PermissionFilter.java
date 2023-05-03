package com.fishep.gateway.filter;

import com.fishep.permission.client.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class PermissionFilter implements GlobalFilter, Ordered {

    @Autowired
    private PermissionService permissionService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("PermissionFilter filter request");

        ServerHttpRequest request = exchange.getRequest();
        String userId = request.getHeaders().getFirst("App-User-Id");
        if (userId == null || userId.isEmpty()) {
            ServerHttpRequest.Builder builder = request.mutate();
            builder.header("App-User-Permissions", "");
            return chain.filter(exchange.mutate().request(builder.build()).build());
        }

        log.info("userId: " + userId);

        String[] userPermissions = permissionService.getUserPermissions(Long.valueOf(userId));

        // @TODO 这个数据可能会有点大，通过请求头传递，并不是很好，考虑用其他的方式
        ServerHttpRequest.Builder builder = request.mutate();
        builder.header("App-User-Permissions", String.join(",", userPermissions));

        return chain.filter(exchange.mutate().request(builder.build()).build());
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
