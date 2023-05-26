package com.fishep.user.infrastructure.dao.impl;

import com.fishep.user.infrastructure.dao.TokenDao;
import com.fishep.user.infrastructure.data.TokenDO;
import com.fishep.user.infrastructure.data.UserDO;
import com.fishep.user.infrastructure.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("PasswordDaoMybatisImpl")
public class PasswordDaoMybatisImpl implements TokenDao {

    @Autowired
    private UserMapper userMapper;

    @Override
    public TokenDO select(UserDO user) {
        if (user.getType() == null) {
            return null;
        }

        UserDO userDO = null;
        if (user.getId() != null) {
            userDO = userMapper.selectById(user);
        }
        if (user.getName() != null) {
            userDO = userMapper.selectByName(user);
        }
        if (user.getEmail() != null) {
            userDO = userMapper.selectByEmail(user);
        }
        if (user.getPhoneNumber() != null) {
            userDO = userMapper.selectByPhoneNumber(user);
        }

        if (userDO != null) {
            return new TokenDO(userDO.getId(), userDO.getPassword());
        }

        return null;
    }
}
