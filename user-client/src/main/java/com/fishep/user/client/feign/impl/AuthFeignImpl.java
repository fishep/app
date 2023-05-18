package com.fishep.user.client.feign.impl;

import com.fishep.common.type.Result;
import com.fishep.user.client.feign.AuthFeign;
import com.fishep.user.response.auth.TokenCheckResponse;
import org.springframework.stereotype.Component;

@Component
public class AuthFeignImpl implements AuthFeign {
    @Override
    public Result<TokenCheckResponse> check(String guard, String authorization) {
        return Result.failure("service degradation, AuthFeignImpl.check(String guard, String authorization), guard: " + guard + ", authorization: " + authorization, null);
    }
}
