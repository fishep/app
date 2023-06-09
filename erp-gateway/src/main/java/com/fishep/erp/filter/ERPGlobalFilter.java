package com.fishep.erp.filter;

import com.alibaba.fastjson.JSON;
import com.fishep.common.exception.ServiceWarn;
import com.fishep.common.type.Guard;
import com.fishep.common.type.Result;
import lombok.extern.slf4j.Slf4j;
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

/**
 * @Author Fly.Fei
 * @Date 2023/5/18 11:55
 * @Desc
 **/
@Slf4j
@Component
public class ERPGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("ERPGlobalFilter filter request");
        try {
            return attachGlobalHttpHeaders(exchange, chain);
        } catch (Exception e) {
            return exceptionResponse(e, exchange.getResponse());
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }

    private Mono<Void> attachGlobalHttpHeaders(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders headers = request.getHeaders();

        // 服务内部全局请求头，禁止客户端使用
        if (headers.getFirst("App-Guard") != null) {
            throw new ServiceWarn("Request header App-Guard is prohibited from being used!");
        }
        if (headers.getFirst("App-User-Type") != null) {
            throw new ServiceWarn("Request header App-User-Type is prohibited from being used!");
        }
        if (headers.getFirst("App-User-Id") != null) {
            throw new ServiceWarn("Request header App-User-Id is prohibited from being used!");
        }
        if (headers.getFirst("App-User-Name") != null) {
            throw new ServiceWarn("Request header App-User-Name is prohibited from being used!");
        }

        // 设置全局请求头
        ServerHttpRequest.Builder builder = request.mutate();
        builder.header("App-Guard", Guard.ERP.toString());

        return chain.filter(exchange.mutate().request(builder.build()).build());
    }

    private Mono<Void> exceptionResponse(Exception e, ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.FORBIDDEN);
        Result<String> result = new Result<>(HttpStatus.FORBIDDEN.value(), e.getMessage(), "gateway forbidden");
        DataBuffer buffer = response.bufferFactory().wrap(JSON.toJSONString(result).getBytes(StandardCharsets.UTF_8));
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");

        return response.writeWith(Mono.just(buffer));
    }
}
