package com.fishep.user.interfaces.converter;

import com.fishep.user.application.dto.UserDTO;
import com.fishep.user.response.auth.LoginResponse;
import com.fishep.user.response.auth.RegisterResponse;
import org.springframework.stereotype.Component;

@Component
public class UserVOConverter {

    public LoginResponse toLoginResponse(UserDTO userDTO) {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setId(userDTO.getId().toString());
        loginResponse.setToken(userDTO.getToken());

        return loginResponse;
    }

    public RegisterResponse toRegisterResponse(UserDTO userDTO) {
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setId(userDTO.getId().toString());
        registerResponse.setToken(userDTO.getToken());

        return registerResponse;
    }

}
