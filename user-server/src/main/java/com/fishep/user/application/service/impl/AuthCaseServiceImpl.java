package com.fishep.user.application.service.impl;

import com.fishep.common.exception.ServiceException;
import com.fishep.user.application.assembler.TokenDTOAssembler;
import com.fishep.user.application.assembler.UserDTOAssembler;
import com.fishep.user.application.dto.LoginDTO;
import com.fishep.user.application.dto.UserDTO;
import com.fishep.user.application.dto.RegisterDTO;
import com.fishep.user.application.service.AuthCaseService;
import com.fishep.user.domain.entity.Token;
import com.fishep.user.domain.entity.User;
import com.fishep.user.domain.repository.UserRepository;
import com.fishep.user.domain.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthCaseServiceImpl implements AuthCaseService {

    @Autowired
    private UserDTOAssembler userDTOAssembler;

    @Autowired
    private TokenDTOAssembler tokenDTOAssembler;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO login(LoginDTO loginDTO) {
        User user = userDTOAssembler.toUser(loginDTO);
        Token token = tokenDTOAssembler.toToken(loginDTO);

        Boolean check = authService.check(user, token);
        if (!check) {
            throw new ServiceException("Login fail");
        }

        // @TODO 生成token
        String authToken = "authToken_" + user.getId().getValue();

        UserDTO userDTO = userDTOAssembler.toLoginedDTO(user, authToken);

        return userDTO;
    }

    @Override
    public UserDTO register(RegisterDTO registerDTO) {
        User user = userDTOAssembler.toUser(registerDTO);

        Boolean flag = userRepository.save(user);
        if (!flag) {
            throw new ServiceException("register fail");
        }

        // @TODO 生成token
        String authToken = "authToken_" + user.getId().getValue();
        UserDTO userDTO = userDTOAssembler.toLoginedDTO(user, authToken);

        return userDTO;
    }
}
