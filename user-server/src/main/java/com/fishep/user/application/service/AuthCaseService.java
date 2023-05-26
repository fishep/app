package com.fishep.user.application.service;

import com.fishep.user.application.dto.LoginDTO;
import com.fishep.user.application.dto.UserDTO;
import com.fishep.user.application.dto.RegisterDTO;

public interface AuthCaseService {

    @Deprecated
    UserDTO login(LoginDTO loginDTO);

    @Deprecated
    UserDTO register(RegisterDTO registerDTO);

    UserDTO adminLoginErp(LoginDTO loginDTO);

    UserDTO adminLoginShop(LoginDTO loginDTO);

    UserDTO customerLoginShop(LoginDTO loginDTO);

    UserDTO customerRegisterShop(RegisterDTO registerDTO);
}
