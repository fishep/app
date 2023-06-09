package com.fishep.user.infrastructure.repository;

import com.fishep.common.exception.NullException;
import com.fishep.user.domain.entity.Code;
import com.fishep.user.domain.entity.Password;
import com.fishep.user.domain.entity.User;
import com.fishep.user.domain.repository.TokenRepository;
import com.fishep.user.infrastructure.assembler.TokenDOAssembler;
import com.fishep.user.infrastructure.assembler.UserDOAssembler;
import com.fishep.user.infrastructure.dao.TokenDao;
import com.fishep.user.infrastructure.data.TokenDO;
import com.fishep.user.type.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class TokenRepositoryImpl implements TokenRepository {

    @Autowired
    private UserDOAssembler userDOAssembler;

    @Autowired
    private TokenDOAssembler tokenDOAssembler;

    @Autowired
    @Qualifier("CodeDaoRedisImpl")
    private TokenDao codeTokenDao;

    @Autowired
    @Qualifier("PasswordDaoMybatisPlusImpl")
//    @Qualifier("PasswordDaoMybatisImpl")
    private TokenDao passwordTokenDao;

    @Override
    public Password findPassword(User user) {
        TokenDO tokenDO = passwordTokenDao.select(userDOAssembler.toUserDO(user));
        if (tokenDO == null) {
            throw new NullException(Message.__(Message.NULL_PASSWORD_FIND_BY, new Object[]{"user", user}));
        }

        return tokenDOAssembler.toPassword(tokenDO);
    }

    @Override
    public Code findCode(User user) {
        TokenDO tokenDO = codeTokenDao.select(userDOAssembler.toUserDO(user));
        if (tokenDO == null) {
            throw new NullException(Message.__(Message.NULL_CODE_FIND_BY, new Object[]{"user", user}));
        }

        return tokenDOAssembler.toCode(tokenDO);
    }
}
