package com.fishep.user.infrastructure.repository;

import com.fishep.common.exception.EntityNullException;
import com.fishep.user.domain.entity.Code;
import com.fishep.user.domain.entity.Password;
import com.fishep.user.domain.entity.User;
import com.fishep.user.domain.repository.TokenRepository;
import com.fishep.user.infrastructure.assembler.TokenDOAssembler;
import com.fishep.user.infrastructure.assembler.UserDOAssembler;
import com.fishep.user.infrastructure.dao.TokenDao;
import com.fishep.user.infrastructure.data.TokenDO;
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
    @Qualifier("PasswordDaoMybatisImpl")
    private TokenDao passwordTokenDao;

    @Override
    public Password findPassword(User user) {
        TokenDO tokenDO = passwordTokenDao.select(userDOAssembler.toUserDO(user));
        if (tokenDO == null) {
            throw new EntityNullException("Password is null, find by user: " + user);
        }

        return tokenDOAssembler.toPassword(tokenDO);
    }

    @Override
    public Code findCode(User user) {
        TokenDO tokenDO = codeTokenDao.select(userDOAssembler.toUserDO(user));
        if (tokenDO == null) {
            throw new EntityNullException("Code is null, find by user: " + user);
        }

        return tokenDOAssembler.toCode(tokenDO);
    }
}
