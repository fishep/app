package com.fishep.user.application.assembler;

import com.fishep.common.exception.EntityNullException;
import com.fishep.common.type.Email;
import com.fishep.common.type.PhoneNumber;
import com.fishep.user.application.dto.LoginDTO;
import com.fishep.user.application.dto.UserDTO;
import com.fishep.user.application.dto.RegisterDTO;
import com.fishep.user.domain.entity.Password;
import com.fishep.user.domain.entity.Token;
import com.fishep.user.domain.entity.User;
import com.fishep.user.domain.repository.TokenRepository;
import com.fishep.user.domain.repository.UserRepository;
import com.fishep.user.type.UserId;
import com.fishep.user.type.UserName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class UserDTOAssembler {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    public User toUser(LoginDTO loginDTO) {
        User user;
        switch (loginDTO.getIdentityType()) {
            case UserName -> user = userRepository.find(new UserName(loginDTO.getIdentity()));
            case Email -> user = userRepository.find(new Email(loginDTO.getIdentity()));
            case PhoneNumber -> user = userRepository.find(new PhoneNumber(loginDTO.getIdentity()));
            default -> throw new IllegalArgumentException("Unknown Identity type, IdentityType: " + loginDTO.getIdentityType());
        }

        if (user == null) {
            throw new EntityNullException("User is null, find by LoginDTO: " + loginDTO);
        }

        Token token;
        switch (loginDTO.getTokenType()) {
            case Password -> token = tokenRepository.findPassword(user);
            case Code -> token = tokenRepository.findCode(user);
            default -> throw new IllegalArgumentException("Unknown Token type, TokenType: " + loginDTO.getTokenType());
        }

        if (token == null) {
            throw new EntityNullException("Token is null, find by LoginDTO: " + loginDTO);
        }

        user.holdToken(token);

        return user;
    }

    public User toUser(RegisterDTO registerDTO) {
        User user = new User(new UserId(), new UserName(registerDTO.getName()), new Email(registerDTO.getEmail()), Instant.now(), Instant.now());
        user.holdToken(new Password(registerDTO.getPassword()));
        return user;
    }

    public UserDTO toLoginedDTO(User user, String token) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId().getValue());
        userDTO.setName(user.getName().getValue());
        userDTO.setEmail(user.getEmail().getValue());
        userDTO.setToken(token);

        return userDTO;
    }
}
