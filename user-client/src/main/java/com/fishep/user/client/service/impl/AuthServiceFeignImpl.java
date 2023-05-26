package com.fishep.user.client.service.impl;

import com.fishep.common.exception.ServiceException;
import com.fishep.common.type.Result;
import com.fishep.user.client.feign.AuthFeign;
import com.fishep.user.client.service.AuthService;
import com.fishep.user.response.auth.TokenCheckResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class AuthServiceFeignImpl implements AuthService {

    @Autowired
    @Lazy
    AuthFeign authFeign;

    @Override
    public TokenCheckResponse check(String guard, String token) {
        /**
         * 在 reactor 里调用 ，必须用异步
         * @TODO 这里必须用异步，why？被gateway调用的必须要用异步？
         * @TODO 有两个线程池会随机执行此处，why？ boundedElastic-5 reactor-http-nio-1
         */
        CompletableFuture<Result<TokenCheckResponse>> future = CompletableFuture.supplyAsync(() -> authFeign.check(guard, token));
        Result<TokenCheckResponse> result = null;
        try {
            result = future.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (result == null || result.getData() == null) {
            throw new ServiceException("Token authentication passed differently");
        }

        return result.getData();
    }
}
