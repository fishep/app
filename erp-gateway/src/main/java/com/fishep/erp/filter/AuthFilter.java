package com.fishep.erp.filter;

import com.alibaba.fastjson.JSON;
import com.fishep.common.exception.ServiceException;
import com.fishep.common.type.Guard;
import com.fishep.common.type.Message;
import com.fishep.common.type.Result;
import com.fishep.user.client.service.AuthService;
import com.fishep.user.response.auth.TokenCheckResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

@Component
@Slf4j
public class AuthFilter implements GlobalFilter, Ordered {

    @Value("${erp.route.un-auth}")
    private String[] unAuthRoutes;

    @Autowired
    private AuthService authService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("AuthFilter filter request");
        try {
            return auth(exchange, chain);
        } catch (Exception e) {
            return exceptionResponse(e, exchange.getResponse());
        }
    }

    @Override
    public int getOrder() {
        return 1;
    }

    private Mono<Void> auth(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders headers = request.getHeaders();
        String uri = request.getPath().value();
        String token = headers.getFirst(HttpHeaders.AUTHORIZATION);

        // 不能在登录的状态下访问的路由
        if (Stream.of(unAuthRoutes).anyMatch(routeRegex -> uri.matches(routeRegex))) {
            if (token != null && !token.isEmpty()) {
                throw new ServiceException(Message.__(Message.TOKEN_REPEAT));
            }
            return chain.filter(exchange);
        }

        // 必须在登录的状态下访问的路由
        if (token == null || token.isEmpty()) {
            throw new ServiceException(Message.__(Message.TOKEN_NO));
        }

        ServerHttpRequest.Builder builder = request.mutate();
        TokenCheckResponse check = authService.check(Guard.ERP.toString(), token);
        builder.header("App-User-Type", check.getUserType());
        builder.header("App-User-Id", String.valueOf(check.getUserId()));
        builder.header("App-User-Name", check.getUserName());

        return chain.filter(exchange.mutate().request(builder.build()).build());
    }

    private Mono<Void> exceptionResponse(Exception e, ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.BAD_REQUEST);
        Result result = new Result<>(HttpStatus.BAD_REQUEST.value(), e.getMessage(), e.getStackTrace());
        DataBuffer buffer = response.bufferFactory().wrap(JSON.toJSONString(result).getBytes(StandardCharsets.UTF_8));
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");

        return response.writeWith(Mono.just(buffer));
    }
}
