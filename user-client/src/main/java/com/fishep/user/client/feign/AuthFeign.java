package com.fishep.user.client.feign;

import com.fishep.common.type.Result;
import com.fishep.user.client.feign.impl.AuthFeignImpl;
import com.fishep.user.response.auth.TokenCheckResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "user-server", contextId = "auth", fallback = AuthFeignImpl.class)
public interface AuthFeign {

    @PostMapping("/api/user/auth/token/check")
    Result<TokenCheckResponse> check(@RequestHeader("App-Guard") String guard, @RequestHeader("Authorization") String authorization);

}
