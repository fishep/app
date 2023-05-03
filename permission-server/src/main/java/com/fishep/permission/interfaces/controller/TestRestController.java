package com.fishep.permission.interfaces.controller;

import com.fishep.common.type.Result;
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
        System.out.println("server.port: " + port);
        return Result.success();
    }

    @GetMapping("/feign")
    public String feign() {
        String ret = testService.api();
        System.out.println("testFeign.api(): " + ret);
        return ret;
    }

    @GetMapping("/feign/permission")
    public String feignPermission() {
        String ret = testService.apiPermission();
        System.out.println("testFeign.apiPermission(): " + ret);
        return ret;
    }
}
