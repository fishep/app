package com.fishep.user.application.assembler;

import com.fishep.common.exception.NullException;
import com.fishep.common.exception.TypeException;
import com.fishep.common.type.Email;
import com.fishep.common.type.PhoneNumber;
import com.fishep.user.application.dto.LoginDTO;
import com.fishep.user.application.dto.RegisterDTO;
import com.fishep.user.application.dto.UserDTO;
import com.fishep.user.domain.entity.*;
import com.fishep.user.domain.repository.TokenRepository;
import com.fishep.user.domain.repository.UserRepository;
import com.fishep.user.type.Message;
import com.fishep.user.type.UserId;
import com.fishep.user.type.UserName;
import com.fishep.user.type.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class UserDTOAssembler {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    public User toUser(UserType userType, LoginDTO loginDTO) {
        User user;
        switch (loginDTO.getIdentityType()) {
            case UserName -> user = userRepository.find(userType, new UserName(loginDTO.getIdentity()));
            case Email -> user = userRepository.find(userType, new Email(loginDTO.getIdentity()));
            case PhoneNumber -> user = userRepository.find(userType, new PhoneNumber(loginDTO.getIdentity()));
            default -> throw new TypeException(Message.__(Message.TYPE_EXCEPTION_IDENTITY, loginDTO.getIdentityType()));
        }

        if (user == null) {
            throw new NullException(Message.__(Message.NULL_USER_FIND_BY, new Object[]{"loginDTO", loginDTO}));
        }

        Token token;
        switch (loginDTO.getTokenType()) {
            case Password -> token = tokenRepository.findPassword(user);
            case Code -> token = tokenRepository.findCode(user);
            default -> throw new TypeException(Message.__(Message.TYPE_EXCEPTION_TOKEN, loginDTO.getTokenType()));
        }

        if (token == null) {
            throw new NullException(Message.__(Message.NULL_TOKEN_FIND_BY, new Object[]{"loginDTO", loginDTO}));
        }

        user.holdToken(token);

        return user;
    }

    public User toUser(UserType userType, RegisterDTO registerDTO) {
        User user;
        switch (userType) {
            case ADMIN -> user = new Admin(new UserId(), new UserName(registerDTO.getName()));
            case CUSTOMER -> user = new Customer(new UserId(), new UserName(registerDTO.getName()));
            case SUPPLIER -> user = new Supplier(new UserId(), new UserName(registerDTO.getName()));
            default -> throw new TypeException(Message.__(Message.TYPE_EXCEPTION_USER, userType));
        }
        user.setEmail(new Email(registerDTO.getEmail()));
        user.holdToken(new Password(registerDTO.getPassword()));
        user.setCreatedAt(Instant.now());
        user.setUpdatedAt(Instant.now());
        return user;
    }

    public UserDTO toUserDTO(User user, String token) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId().getValue());
        userDTO.setName(user.getName().getValue());
        userDTO.setEmail(user.getEmail().getValue());
        userDTO.setToken(token);

        return userDTO;
    }
}
