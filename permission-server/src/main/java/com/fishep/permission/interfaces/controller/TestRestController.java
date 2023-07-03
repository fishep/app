package com.fishep.permission.interfaces.controller;

import com.fishep.common.type.Result;
import com.fishep.permission.annotation.Permission;
import com.fishep.user.client.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestRestController {

    @Value("${server.port}")
    private Integer port;

    @Autowired
    private TestService testService;

    @GetMapping("/api")
    public Result api() {
        return Result.success();
    }

    @GetMapping("/feign")
    public String feign() {
        String ret = testService.api();
        return ret;
    }

    @GetMapping("/feign/permission")
    public String feignPermission() {
        String ret = testService.apiPermission();
        return ret;
    }

    @GetMapping("/permission/check")
    @Permission("permission.test.permission.check")
    public String permission() {
        return "权限校验通过";
    }
}
