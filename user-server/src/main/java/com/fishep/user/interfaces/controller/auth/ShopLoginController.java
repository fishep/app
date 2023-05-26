package com.fishep.user.interfaces.controller.auth;

import com.fishep.server.annotation.ShopGuard;
import com.fishep.user.application.dto.LoginDTO;
import com.fishep.user.application.dto.UserDTO;
import com.fishep.user.application.service.AuthCaseService;
import com.fishep.user.interfaces.converter.UserVOConverter;
import com.fishep.user.request.auth.LoginRequest;
import com.fishep.user.response.auth.LoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商城登录
 * 1, 客户登录商城
 * 2, 管理员登录商城（erp后台用户，公司内部员工）
 */
@Slf4j
@ShopGuard
@RestController
@RequestMapping("/auth/shop/login")
public class ShopLoginController {

    @Autowired
    AuthCaseService authCaseService;

    @Autowired
    UserVOConverter userVOConverter;

    @PostMapping("/customer")
    public LoginResponse loginCustomer(@Validated @RequestBody LoginRequest request) {
        log.info("customer login shop");

        LoginDTO loginDTO = new LoginDTO(request.getIdentity(), request.getToken());

        UserDTO userDTO = authCaseService.customerLoginShop(loginDTO);

        return userVOConverter.toLoginResponse(userDTO);
    }

    @PostMapping("/admin")
    public LoginResponse loginAdmin(@Validated @RequestBody LoginRequest request) {
        log.info("admin login shop");

        LoginDTO loginDTO = new LoginDTO(request.getIdentity(), request.getToken());

        UserDTO userDTO = authCaseService.adminLoginShop(loginDTO);

        return userVOConverter.toLoginResponse(userDTO);
    }

}
