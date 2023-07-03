package com.fishep.user.interfaces.controller;

import com.fishep.common.exception.ServiceError;
import com.fishep.common.exception.ServiceException;
import com.fishep.common.exception.ServiceWarn;
import com.fishep.common.type.Result;
import com.fishep.permission.annotation.Permission;
import com.fishep.permission.annotation.Permissions;
import com.fishep.server.annotation.CustomFormat;
import com.fishep.user.request.TestRequest;
import com.fishep.user.type.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Deprecated
@RestController
@RequestMapping("/test")
//@Permission("user.test")
public class TestRestController {

    @Value("${server.port}")
    private Integer port;

    @GetMapping("/api")
    public Result api() {
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
    @Permission("user.test.api.permission.apiPermission")
    public String apiPermission() {
        return "hello permission!";
    }

    @GetMapping("/api/permission/array")
    @Permission("user.test.api.permission.array.apiPermissionArray.1")
    @Permission("user.test.api.permission.array.apiPermissionArray.2")
    public String apiPermissionArray() {
        return "hello permission array!";
    }

    @GetMapping("/api/permissions")
    @Permissions(values = {"user.test.api.permissions.1", "user.test.api.permissions.2"})
    public String apiPermissions() {
        return "hello permissions !";
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
        throw new ServiceException(Message.__(Message.TEST_SERVICE_EXCEPTION));
    }

    @GetMapping("/throw/warn")
    public Result testThrowWarn() {
        throw new ServiceWarn(Message.__(Message.TEST_SERVICE_WARN));
    }

    @GetMapping("/throw/error")
    public Result testThrowError() {
        throw new ServiceError(Message.__(Message.TEST_SERVICE_ERROR));
    }

    @GetMapping("/i18n/string")
    public String localeString() {
        return Message.__(Message.HELLO, "world");
    }

    @GetMapping("/i18n/result")
    public Result localeResult() {
        return Result.success(Message.__("hello", "世界"));
    }
}
