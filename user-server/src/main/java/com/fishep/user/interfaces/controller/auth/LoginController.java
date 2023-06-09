package com.fishep.user.interfaces.controller.auth;

import com.fishep.user.application.dto.LoginDTO;
import com.fishep.user.application.dto.UserDTO;
import com.fishep.user.application.service.AuthCaseService;
import com.fishep.user.interfaces.converter.UserVOConverter;
import com.fishep.user.request.auth.CodeLoginRequest;
import com.fishep.user.request.auth.LoginRequest;
import com.fishep.user.request.auth.PasswordLoginRequest;
import com.fishep.user.response.auth.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Deprecated
//@RestController
//@RequestMapping("/auth/login")
public class LoginController {

    @Autowired
    AuthCaseService authCaseService;

    @Autowired
    UserVOConverter userVOConverter;

    // 登录
    @PostMapping("/")
    public LoginResponse login(@Validated @RequestBody LoginRequest request) {
        LoginDTO loginDTO = new LoginDTO(request.getIdentity(), request.getToken());

        UserDTO userDTO = authCaseService.login(loginDTO);

        return userVOConverter.toLoginResponse(userDTO);
    }

    // 密码登录
    @PostMapping("/passwordLogin")
    public LoginResponse passwordLogin(@Validated @RequestBody PasswordLoginRequest request) {
        LoginDTO loginDTO = new LoginDTO(request.getIdentity(), request.getToken(), LoginDTO.TokenType.Password);

        UserDTO userDTO = authCaseService.login(loginDTO);

        return userVOConverter.toLoginResponse(userDTO);
    }

    // 验证码登录
    @PostMapping("/codeLogin")
    public LoginResponse codeLogin(@Validated @RequestBody CodeLoginRequest request) {
        LoginDTO loginDTO = new LoginDTO(request.getIdentity(), request.getToken(), LoginDTO.TokenType.Code);

        UserDTO userDTO = authCaseService.login(loginDTO);

        return userVOConverter.toLoginResponse(userDTO);
    }

}
