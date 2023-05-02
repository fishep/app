package com.fishep.user.client.feign;

import com.fishep.common.type.Result;
import com.fishep.user.client.feign.impl.TestFeignImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "user-server", contextId = "test", fallback = TestFeignImpl.class)
public interface TestFeign {

    @GetMapping("/api/user/test/api")
    Result api();

}
