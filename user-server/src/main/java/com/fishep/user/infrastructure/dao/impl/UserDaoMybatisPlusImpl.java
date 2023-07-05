package com.fishep.user.infrastructure.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fishep.user.infrastructure.dao.UserDao;
import com.fishep.user.infrastructure.data.UserDO;
import com.fishep.user.infrastructure.mapper.UserPlusMapper;
import com.fishep.user.type.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserDaoMybatisPlusImpl implements UserDao {

    @Autowired
    private UserPlusMapper userPlusMapper;

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

        int r = userPlusMapper.insert(user);

        return r == 1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public Boolean delete(UserDO user) {
        if (user.getType() == null) {
            return Boolean.FALSE;
        }

        int r = 0;

        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("type", user.getType());
        if (user.getId() != null) {
            columnMap.put("id", user.getId());
        } else if (user.getName() != null) {
            columnMap.put("name", user.getName());
        } else if (user.getEmail() != null) {
            columnMap.put("email", user.getEmail());
        } else if (user.getPhoneNumber() != null) {
            columnMap.put("phone_number", user.getPhoneNumber());
        } else {
            return Boolean.FALSE;
        }

        r = userPlusMapper.deleteByMap(columnMap);

        return r == 1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public Boolean update(UserDO user) {
        if (user.getType() == null || user.getId() == null || user.getName() == null) {
            return Boolean.FALSE;
        }
        user.setUpdatedAt(Instant.now().toEpochMilli());

        QueryWrapper<UserDO> wrapper = new QueryWrapper<>();
        wrapper.eq("type", user.getType());
        wrapper.eq("id", user.getId());

        int r = userPlusMapper.update(user, wrapper);

        return r == 1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public Boolean updatePassword(UserDO user) {
        if (user.getType() == null || user.getId() == null) {
            return Boolean.FALSE;
        }

        QueryWrapper<UserDO> wrapper = new QueryWrapper<>();
        wrapper.eq("type", user.getType());
        wrapper.eq("id", user.getId());

        UserDO u = new UserDO();
        u.setPassword(user.getPassword());
        u.setUpdatedAt(Instant.now().toEpochMilli());

        int r = userPlusMapper.update(u, wrapper);

        return r == 1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public UserDO select(UserDO user) {
        if (user.getType() == null) {
            return null;
        }

        QueryWrapper<UserDO> wrapper = new QueryWrapper<>();
        wrapper.eq("type", user.getType());
        if (user.getId() != null) {
            wrapper.eq("id", user.getId());
        } else if (user.getName() != null) {
            wrapper.eq("name", user.getName());
        } else if (user.getEmail() != null) {
            wrapper.eq("email", user.getEmail());
        } else if (user.getPhoneNumber() != null) {
            wrapper.eq("phone_number", user.getPhoneNumber());
        } else {
            return null;
        }

        return userPlusMapper.selectOne(wrapper);
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
