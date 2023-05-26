package com.fishep.user.infrastructure.dao.impl;

import com.fishep.user.infrastructure.dao.UserDao;
import com.fishep.user.infrastructure.data.UserDO;
import com.fishep.user.infrastructure.mapper.UserMapper;
import com.fishep.user.type.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class UserDaoMybatisImpl implements UserDao {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Boolean insert(UserDO user) {
        if (user.getType() == null || user.getName() == null) {
            return Boolean.FALSE;
        }
        if (user.getId() == null) {
            user.setId(new UserId().getValue());
        }
        if (user.getCreatedAt() == null) {
            user.setCreatedAt(Instant.now().toEpochMilli());
        }
        if (user.getUpdatedAt() == null) {
            user.setUpdatedAt(Instant.now().toEpochMilli());
        }

        Boolean flag = userMapper.insert(user);

        return flag;
    }

    @Override
    public Boolean delete(UserDO user) {
        if (user.getType() == null) {
            return Boolean.FALSE;
        }
        if (user.getId() != null) {
            return userMapper.deleteById(user);
        }
        if (user.getName() != null) {
            return userMapper.deleteByName(user);
        }
        if (user.getEmail() != null) {
            return userMapper.deleteByEmail(user);
        }
        if (user.getPhoneNumber() != null) {
            return userMapper.deleteByPhoneNumber(user);
        }

        return Boolean.FALSE;
    }

    @Override
    public Boolean update(UserDO user) {
        if (user.getType() == null || user.getId() == null || user.getName() == null) {
            return Boolean.FALSE;
        }

        user.setUpdatedAt(Instant.now().toEpochMilli());

        Boolean flag = userMapper.update(user);

        return flag;
    }

    @Override
    public Boolean updatePassword(UserDO user) {
        if (user.getType() == null || user.getId() == null) {
            return Boolean.FALSE;
        }

        user.setUpdatedAt(Instant.now().toEpochMilli());

        Boolean flag = userMapper.updatePassword(user);

        return flag;
    }

    @Override
    public UserDO select(UserDO user) {
        if (user.getType() == null) {
            return null;
        }
        if (user.getId() != null) {
            return userMapper.selectById(user);
        }
        if (user.getName() != null) {
            return userMapper.selectByName(user);
        }
        if (user.getEmail() != null) {
            return userMapper.selectByEmail(user);
        }
        if (user.getPhoneNumber() != null) {
            return userMapper.selectByPhoneNumber(user);
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
