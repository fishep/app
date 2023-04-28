package com.fishep.user.client.service.impl;

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
    public Long check(String token) {
        CompletableFuture<Result<TokenCheckResponse>> future = CompletableFuture.supplyAsync(() -> authFeign.check(token));
        Result<TokenCheckResponse> result = null;
        try {
            result = future.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (result == null || result.getData() == null || result.getData().getFlag() == Boolean.FALSE || result.getData().getUserId() == null || result.getData().getUserId() <= 0) {
            throw new RuntimeException(result.getMessage());
        }

        return result.getData().getUserId();
    }
}
