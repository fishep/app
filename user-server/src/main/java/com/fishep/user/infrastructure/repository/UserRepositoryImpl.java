package com.fishep.user.infrastructure.repository;

import com.fishep.common.exception.EntityNullException;
import com.fishep.common.type.Email;
import com.fishep.common.type.PhoneNumber;
import com.fishep.user.domain.entity.User;
import com.fishep.user.domain.repository.UserRepository;
import com.fishep.user.infrastructure.assembler.UserDOAssembler;
import com.fishep.user.infrastructure.dao.UserDao;
import com.fishep.user.infrastructure.data.UserDO;
import com.fishep.user.type.UserId;
import com.fishep.user.type.UserName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    UserDao userDao;

    @Autowired
    UserDOAssembler userDOAssembler;

    @Override
    public Boolean save(User user) {
        UserDO userDO = userDOAssembler.toUserDO(user);

        Boolean flag = userDao.insert(userDO);

        return flag;
    }

    @Override
    public User find(UserId id) {
        UserDO u = new UserDO();
        u.setId(id.getValue());

        UserDO userDO = userDao.select(u);
        if (userDO != null) {
            return userDOAssembler.toUser(userDO);
        }

        return null;
    }

    @Override
    public User findOrException(UserId id) {
        User user = this.find(id);
        if (user == null) {
            throw new EntityNullException("User is null, find by UserId: " + id.getValue());
        }
        return user;
    }

    @Override
    public User find(UserName name) {
        UserDO u = new UserDO();
        u.setName(name.getValue());

        UserDO userDO = userDao.select(u);
        if (userDO != null) {
            return userDOAssembler.toUser(userDO);
        }

        return null;
    }

    @Override
    public User findOrException(UserName name) {
        User user = this.find(name);
        if (user == null) {
            throw new EntityNullException("User is null, find by UserName: " + name.getValue());
        }
        return user;
    }

    @Override
    public User find(Email email) {
        UserDO u = new UserDO();
        u.setEmail(email.getValue());

        UserDO userDO = userDao.select(u);
        if (userDO != null) {
            return userDOAssembler.toUser(userDO);
        }

        return null;
    }

    @Override
    public User findOrException(Email email) {
        User user = this.find(email);
        if (user == null) {
            throw new EntityNullException("User is null, find by Email: " + email.getValue());
        }
        return user;
    }

    @Override
    public User find(PhoneNumber phoneNumber) {
        return null;
    }

    @Override
    public User findOrException(PhoneNumber phoneNumber) {
        User user = this.find(phoneNumber);
        if (user == null) {
            throw new EntityNullException("User is null, find by PhoneNumber: " + phoneNumber.getValue());
        }
        return user;
    }
}
