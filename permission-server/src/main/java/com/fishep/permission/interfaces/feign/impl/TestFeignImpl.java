package com.fishep.permission.interfaces.feign.impl;

import com.fishep.permission.interfaces.feign.TestFeign;
import org.springframework.stereotype.Component;

@Component
public class TestFeignImpl implements TestFeign {
    @Override
    public String apiString() {
        return "service degradation, TestFeignImpl.apiString()";
    }
}
