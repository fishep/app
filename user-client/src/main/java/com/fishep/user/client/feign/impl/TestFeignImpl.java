package com.fishep.user.client.feign.impl;

import com.fishep.common.type.Result;
import com.fishep.user.client.feign.TestFeign;
import com.fishep.user.type.Message;
import org.springframework.stereotype.Component;

@Component
public class TestFeignImpl implements TestFeign {
    @Override
    public Result api() {
        return Result.failure(Message.__(Message.FALLBACK, "TestFeignImpl.api()"), null);
    }

    @Override
    public Result<String> apiPermission() {
        return Result.failure(Message.__(Message.FALLBACK, "TestFeignImpl.apiPermission()"), null);
    }
}
