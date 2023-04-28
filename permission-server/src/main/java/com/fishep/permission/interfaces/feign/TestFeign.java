package com.fishep.permission.interfaces.feign;

import com.fishep.permission.interfaces.feign.impl.TestFeignImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "user-server", fallback = TestFeignImpl.class)
public interface TestFeign {

    @GetMapping("/api/user/test/api/string")
    String apiString();

}
