package com.fishep.user.infrastructure.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fishep.user.infrastructure.dao.TokenDao;
import com.fishep.user.infrastructure.data.TokenDO;
import com.fishep.user.infrastructure.data.UserDO;
import com.fishep.user.infrastructure.mapper.UserPlusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("PasswordDaoMybatisPlusImpl")
public class PasswordDaoMybatisPlusImpl implements TokenDao {

    @Autowired
    private UserPlusMapper userPlusMapper;

    @Override
    public TokenDO select(UserDO user) {
        if (user.getType() == null) {
            return null;
        }

        UserDO userDO = null;

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

        userDO = userPlusMapper.selectOne(wrapper);

        if (userDO != null) {
            return new TokenDO(userDO.getId(), userDO.getPassword());
        }

        return null;
    }
}
