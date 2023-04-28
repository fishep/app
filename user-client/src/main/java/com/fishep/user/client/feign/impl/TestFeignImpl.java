package com.fishep.user.client.feign.impl;

import com.fishep.user.client.feign.TestFeign;
import org.springframework.stereotype.Component;

@Component
public class TestFeignImpl implements TestFeign {
    @Override
    public String apiString() {
        return "service degradation, TestFeignImpl.apiString()";
    }
}
