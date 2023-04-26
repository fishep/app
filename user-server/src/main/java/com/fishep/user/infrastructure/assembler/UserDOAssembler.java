package com.fishep.user.infrastructure.assembler;

import com.fishep.common.type.Email;
import com.fishep.user.domain.entity.User;
import com.fishep.user.infrastructure.data.UserDO;
import com.fishep.user.type.UserId;
import com.fishep.user.type.UserName;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class UserDOAssembler {

    public User toUser(UserDO userDO) {
        return new User(new UserId(userDO.getId()), new UserName(userDO.getName()), new Email(userDO.getEmail()), Instant.ofEpochMilli(userDO.getCreatedAt()), Instant.ofEpochMilli(userDO.getUpdatedAt()));
    }

    public UserDO toUserDO(User user) {
        UserDO userDO = new UserDO();
        userDO.setId(user.getId().getValue());
        userDO.setName(user.getName().getValue());
        userDO.setEmail(user.getEmail().getValue());
        userDO.setPassword(user.getToken() != null ? user.getToken().getCheckValue() : null);
        userDO.setCreatedAt(user.getCreatedAt().toEpochMilli());
        userDO.setUpdatedAt(user.getUpdatedAt().toEpochMilli());

        return userDO;
    }

}
