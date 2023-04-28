package com.fishep.permission.interfaces.controller;

import com.fishep.common.type.Result;
import com.fishep.permission.interfaces.feign.TestFeign;
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
    private TestFeign testFeign;

    @GetMapping("/api")
    public Result api() {
        System.out.println("server.port: " + port);
        return Result.success();
    }

    @GetMapping("/feign")
    public String feign() {
        String s = testFeign.apiString();
        System.out.println("testFeign.apiString(): " + s);
        return s;
    }
}
