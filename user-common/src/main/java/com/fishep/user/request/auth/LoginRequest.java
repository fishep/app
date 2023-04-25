package com.fishep.user.request.auth;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 登录请求
 * 可能使用， 用户名，邮箱，电话号码 做为唯一标识
 * 可能使用， 密码，验证码         做为验证依据
 */
@Data
public class LoginRequest {

    @NotNull
    @NotEmpty
    public String identity;

    @NotNull
    @NotEmpty
    public String token;
}
