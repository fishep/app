package com.fishep.user.application.service;

import com.fishep.user.application.dto.LoginDTO;
import com.fishep.user.application.dto.UserDTO;
import com.fishep.user.application.dto.RegisterDTO;

public interface AuthCaseService {

    UserDTO login(LoginDTO loginDTO);

//    UserDTO loginBindSession(LoginDTO loginDTO);

//    UserDTO loginBindJwt(LoginDTO loginDTO);

    UserDTO register(RegisterDTO registerDTO);
}
