package com.fishep.user.application.assembler;

import com.fishep.user.application.dto.LoginDTO;
import com.fishep.user.domain.entity.Code;
import com.fishep.user.domain.entity.Password;
import com.fishep.user.domain.entity.Token;
import org.springframework.stereotype.Component;

@Component
public class TokenDTOAssembler {

    public Token toToken(LoginDTO loginDTO) {
        Token token;
        switch (loginDTO.getTokenType()) {
            case Password -> token = new Password(loginDTO.getToken());
            case Code -> token = new Code(loginDTO.getToken());
            default -> throw new IllegalArgumentException("Unknown Token type, TokenType: " + loginDTO.getTokenType());
        }

        return token;
    }
}
