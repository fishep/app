package com.fishep.user.application.service.impl;

import com.fishep.common.exception.TypeException;
import com.fishep.common.exception.ServiceException;
import com.fishep.common.type.Guard;
import com.fishep.user.application.assembler.TokenDTOAssembler;
import com.fishep.user.application.assembler.UserDTOAssembler;
import com.fishep.user.application.dto.LoginDTO;
import com.fishep.user.application.dto.RegisterDTO;
import com.fishep.user.application.dto.UserDTO;
import com.fishep.user.application.service.AuthCaseService;
import com.fishep.user.domain.entity.*;
import com.fishep.user.domain.repository.UserRepository;
import com.fishep.user.domain.service.AuthService;
import com.fishep.user.type.Message;
import com.fishep.user.type.UserType;
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
        return null;
    }

    @Override
    public UserDTO register(RegisterDTO registerDTO) {
        return null;
    }

    @Override
    public UserDTO adminLoginErp(LoginDTO loginDTO) {
        User user = this.doLogin(UserType.ADMIN, loginDTO);

        String accessToken = this.createAccessToken(Guard.ERP, user);

        UserDTO userDTO = userDTOAssembler.toUserDTO(user, accessToken);

        return userDTO;
    }

    @Override
    public UserDTO adminLoginShop(LoginDTO loginDTO) {
        User user = this.doLogin(UserType.ADMIN, loginDTO);

        String accessToken = this.createAccessToken(Guard.SHOP, user);

        UserDTO userDTO = userDTOAssembler.toUserDTO(user, accessToken);

        return userDTO;
    }

    @Override
    public UserDTO customerLoginShop(LoginDTO loginDTO) {
        User user = this.doLogin(UserType.CUSTOMER, loginDTO);

        String accessToken = this.createAccessToken(Guard.SHOP, user);

        UserDTO userDTO = userDTOAssembler.toUserDTO(user, accessToken);

        return userDTO;
    }

    private User doLogin(UserType userType, LoginDTO loginDTO) {
        User user = userDTOAssembler.toUser(userType, loginDTO);
        Token token = tokenDTOAssembler.toToken(loginDTO);

        Boolean check = authService.check(user, token);
        if (!check) {
            throw new ServiceException(Message.__(Message.LOGIN_FAIL));
        }

        return user;
    }

    @Override
    public UserDTO customerRegisterShop(RegisterDTO registerDTO) {
        User user = userDTOAssembler.toUser(UserType.CUSTOMER, registerDTO);

        Boolean flag = userRepository.save(user);
        if (!flag) {
            throw new ServiceException(Message.__(Message.REGISTER_FAIL));
        }

        String accessToken = this.createAccessToken(Guard.SHOP, user);

        UserDTO userDTO = userDTOAssembler.toUserDTO(user, accessToken);

        return userDTO;
    }

    private String createAccessToken(Guard guard, User user) {
        UserType type;
        if (user instanceof Admin) {
            type = UserType.ADMIN;
        } else if (user instanceof Customer) {
            type = UserType.CUSTOMER;
        } else if (user instanceof Supplier) {
            type = UserType.SUPPLIER;
        } else {
            throw new TypeException(Message.__(Message.TYPE_EXCEPTION_USER, user));
        }

        // @TODO 生成jwt
        String jwt = "ver:1"; // jwt 版本号，如果希望以前发布的失效，把数字加1
        jwt += " guard:" + guard ;
        jwt += " type:" + type ;
        jwt += " id:" + user.getId().getValue();
        jwt += " name:" + user.getName().getValue();

        return jwt;
    }
}
