package com.fishep.user.infrastructure.assembler;

import com.fishep.user.domain.entity.Code;
import com.fishep.user.domain.entity.Password;
import com.fishep.user.infrastructure.data.TokenDO;
import org.springframework.stereotype.Component;

@Component
public class TokenDOAssembler {

    public Code toCode(TokenDO tokenDO) {
        return new Code(tokenDO.getValue());
    }

    public Password toPassword(TokenDO tokenDO) {
        return Password.hash(tokenDO.getValue());
    }
}
