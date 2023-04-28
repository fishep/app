package com.fishep.user.interfaces.controller.auth;

import com.fishep.common.exception.ValidateException;
import com.fishep.user.application.dto.RegisterDTO;
import com.fishep.user.application.dto.UserDTO;
import com.fishep.user.application.service.AuthCaseService;
import com.fishep.user.interfaces.converter.UserVOConverter;
import com.fishep.user.request.auth.RegisterRequest;
import com.fishep.user.response.auth.RegisterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/register")
public class RegisterController {

    @Autowired
    AuthCaseService authCaseService;

    @Autowired
    UserVOConverter userVOConverter;

    // 注册
    @PostMapping("")
    public RegisterResponse register(@Validated @RequestBody RegisterRequest request) {
        if (!request.passwordConfirm()) {
            throw new ValidateException("Password inconsistency");
        }

        RegisterDTO registerDTO = new RegisterDTO(request.getName(), request.getEmail(), request.getPassword());

        UserDTO userDTO = authCaseService.register(registerDTO);

        return userVOConverter.toRegisterResponse(userDTO);
    }

}
