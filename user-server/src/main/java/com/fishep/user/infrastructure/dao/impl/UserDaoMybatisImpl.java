package com.fishep.user.infrastructure.dao.impl;

import com.fishep.user.infrastructure.dao.UserDao;
import com.fishep.user.infrastructure.data.UserDO;
import com.fishep.user.infrastructure.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDaoMybatisImpl implements UserDao {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Boolean insert(UserDO user) {
        Boolean flag = userMapper.insert(user);

        return flag;
    }

    @Override
    public Boolean delete(UserDO user) {
        if (user.getId() != null) {
            return userMapper.deleteById(user.getId());
        }
        if (user.getName() != null) {
            return userMapper.deleteByName(user.getName());
        }
        if (user.getEmail() != null) {
            return userMapper.deleteByEmail(user.getEmail());
        }

        return Boolean.FALSE;
    }

    @Override
    public Boolean update(UserDO user) {
        Boolean flag = userMapper.update(user);

        return flag;
    }

    @Override
    public Boolean updatePassword(UserDO user) {
        Boolean flag = userMapper.updatePassword(user);

        return flag;
    }

    @Override
    public UserDO select(UserDO user) {
        if (user.getId() != null) {
            return userMapper.selectById(user.getId());
        }
        if (user.getName() != null) {
            return userMapper.selectByName(user.getName());
        }
        if (user.getEmail() != null) {
            return userMapper.selectByEmail(user.getEmail());
        }

        return null;
    }

    @Override
    public Boolean isExist(UserDO user) {
        Boolean flag = Boolean.FALSE;

        if (this.select(user) != null) {
            flag = Boolean.TRUE;
        }

        return flag;
    }
}
