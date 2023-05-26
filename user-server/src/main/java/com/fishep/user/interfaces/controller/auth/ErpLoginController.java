package com.fishep.user.interfaces.controller.auth;

import com.fishep.server.annotation.ErpGuard;
import com.fishep.user.application.dto.LoginDTO;
import com.fishep.user.application.dto.UserDTO;
import com.fishep.user.application.service.AuthCaseService;
import com.fishep.user.interfaces.converter.UserVOConverter;
import com.fishep.user.request.auth.CodeLoginRequest;
import com.fishep.user.request.auth.LoginRequest;
import com.fishep.user.request.auth.PasswordLoginRequest;
import com.fishep.user.response.auth.LoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 后台erp登录
 * 1, 管理员登录后台（erp后台用户，公司内部员工）
 */
@Slf4j
@ErpGuard
@RestController
@RequestMapping("/auth/erp/login/admin")
public class ErpLoginController {

    @Autowired
    AuthCaseService authCaseService;

    @Autowired
    UserVOConverter userVOConverter;

    // token 可以是 password 或 code
    @PostMapping("/token")
    public LoginResponse adminLoginErpByToken(@Validated @RequestBody LoginRequest request) {
        log.info("admin login erp by token");

        LoginDTO loginDTO = new LoginDTO(request.getIdentity(), request.getToken());

        UserDTO userDTO = authCaseService.adminLoginErp(loginDTO);

        return userVOConverter.toLoginResponse(userDTO);
    }

    // 密码登录
    @PostMapping("/password")
    public LoginResponse adminLoginErpByPassword(@Validated @RequestBody PasswordLoginRequest request) {
        log.info("admin login erp by password");

        LoginDTO loginDTO = new LoginDTO(request.getIdentity(), request.getToken(), LoginDTO.TokenType.Password);

        UserDTO userDTO = authCaseService.adminLoginErp(loginDTO);

        return userVOConverter.toLoginResponse(userDTO);
    }

    // 验证码登录
    @PostMapping("/code")
    public LoginResponse adminLoginErpByCode(@Validated @RequestBody CodeLoginRequest request) {
        log.info("admin login erp by code");

        LoginDTO loginDTO = new LoginDTO(request.getIdentity(), request.getToken(), LoginDTO.TokenType.Code);

        UserDTO userDTO = authCaseService.adminLoginErp(loginDTO);

        return userVOConverter.toLoginResponse(userDTO);
    }

}
