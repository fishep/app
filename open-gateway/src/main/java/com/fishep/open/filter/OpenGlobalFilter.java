package com.fishep.open.filter;

import com.fishep.common.exception.ServiceException;
import com.fishep.common.type.Guard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Author Fly.Fei
 * @Date 2023/5/18 11:55
 * @Desc
 **/
@Slf4j
@Component
public class OpenGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("OpenGlobalFilter filter request");

        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders headers = request.getHeaders();

        // 服务内部全局请求头，禁止客户端使用
        if (headers.getFirst("App-Guard") != null) {
            throw new ServiceException("Request header App-Guard is prohibited from being used!");
        }
        if (headers.getFirst("App-User-Type") != null) {
            throw new ServiceException("Request header App-User-Type is prohibited from being used!");
        }
        if (headers.getFirst("App-User-Id") != null) {
            throw new ServiceException("Request header App-User-Id is prohibited from being used!");
        }
        if (headers.getFirst("App-User-Name") != null) {
            throw new ServiceException("Request header App-User-Name is prohibited from being used!");
        }

        // 设置全局请求头
        ServerHttpRequest.Builder builder = request.mutate();
        builder.header("App-Guard", Guard.OPEN.toString());

        return chain.filter(exchange.mutate().request(builder.build()).build());
    }


    @Override
    public int getOrder() {
        return 0;
    }
}
