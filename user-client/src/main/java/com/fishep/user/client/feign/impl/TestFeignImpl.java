package com.fishep.user.client.feign.impl;

import com.fishep.common.type.Result;
import com.fishep.user.client.feign.TestFeign;
import org.springframework.stereotype.Component;

@Component
public class TestFeignImpl implements TestFeign {
    @Override
    public Result api() {
        return Result.failure("service degradation, TestFeignImpl.api()");
    }

    @Override
    public Result<String> apiPermission() {
        return Result.failure("service degradation, TestFeignImpl.apiPermission()");
    }
}
