package com.fishep.user.client.feign.impl;

import com.fishep.common.type.Result;
import com.fishep.user.client.feign.AuthFeign;
import com.fishep.user.response.auth.TokenCheckResponse;
import com.fishep.user.type.Message;
import org.springframework.stereotype.Component;

@Component
public class AuthFeignImpl implements AuthFeign {
    @Override
    public Result<TokenCheckResponse> check(String guard, String authorization, String locale) {
        return Result.failure(Message.__(Message.FALLBACK, "AuthFeignImpl.check(String guard, String authorization), guard: " + guard + ", authorization: " + authorization + ", locale: " + locale), null);
    }
}
