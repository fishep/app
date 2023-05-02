package com.fishep.user.interfaces.controller;

import com.fishep.common.exception.AppException;
import com.fishep.common.type.Result;
import com.fishep.common.type.StatusCode;
import com.fishep.server.annotation.CustomFormat;
import com.fishep.server.annotation.Permission;
import com.fishep.server.annotation.Permissions;
import com.fishep.user.request.TestRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@Permission("test.api.permission")
public class TestRestController {

    @Value("${server.port}")
    private Integer port;

    @GetMapping("/api")
    public Result api() {
        System.out.println("api(), server.port: " + port);
        return Result.success();
    }

    @GetMapping("/api/data")
    public String apiData() {
        return "hello api!";
    }

    @CustomFormat
    @GetMapping("/api/string")
    public String apiString() {
        return "hello api!";
    }

    @GetMapping("/api/permission")
    @Permissions(values = {"test.api.permission1", "test.api.permission2"})
    public String apiPermission() {
        return "hello permission!";
    }

    @GetMapping("/http/header")
    public String header(@RequestHeader("App-User-Id") String userId, @RequestHeader("App-User-Name") String userName) {
        return "App-User-Id: " + userId + "; App-User-Name: " + userName;
    }

    @GetMapping("/validate")
    // 要求TestRequest 必须有 get set 方法，才能完成所有GET参数的映射
    public Result testRequest(@Validated TestRequest request) {
        return Result.success();
    }

    @GetMapping("/validate/id")
    public Result testRequestParam(@Validated @RequestParam("id") Long id) {
        return Result.success();
    }

    @GetMapping("/validate/{id}")
    public Result testPathVariable(@Validated @PathVariable("id") Long id) {
        return Result.success();
    }

    @PostMapping("/validate")
    public Result testRequestBody(@Validated @RequestBody TestRequest request) {
        return Result.success();
    }

    @GetMapping("/throw/exception")
    public Result testThrowException() {
        throw new AppException(StatusCode.FAILURE.getCode(), "something is worry!");
    }

}
