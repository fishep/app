package com.fishep.permission.interfaces.feign.impl;

import com.fishep.common.type.Result;
import com.fishep.permission.interfaces.feign.TestFeign;
import org.springframework.stereotype.Component;

@Component
public class TestFeignImpl implements TestFeign {
    @Override
    public Result api() {
        return Result.failure("service degradation, TestFeignImpl.api()", null);
    }
}
