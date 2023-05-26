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
import com.fishep.user.type.UserType;
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
    public User find(UserType type, UserId id) {
        UserDO u = new UserDO();
        u.setType(type.toString());
        u.setId(id.getValue());

        return doSelectAndAssembler(u);
    }

    @Override
    public User findOrException(UserType type, UserId id) {
        User user = this.find(type, id);
        if (user == null) {
            throw new EntityNullException("User is null, find by UserId: " + id.getValue());
        }
        return user;
    }

    @Override
    public User find(UserType type, UserName name) {
        UserDO u = new UserDO();
        u.setType(type.toString());
        u.setName(name.getValue());

        return doSelectAndAssembler(u);
    }

    @Override
    public User findOrException(UserType type, UserName name) {
        User user = this.find(type, name);
        if (user == null) {
            throw new EntityNullException("User is null, find by UserName: " + name.getValue());
        }
        return user;
    }

    @Override
    public User find(UserType type, Email email) {
        UserDO u = new UserDO();
        u.setType(type.toString());
        u.setEmail(email.getValue());

        return doSelectAndAssembler(u);
    }

    @Override
    public User findOrException(UserType type, Email email) {
        User user = this.find(type, email);
        if (user == null) {
            throw new EntityNullException("User is null, find by Email: " + email.getValue());
        }
        return user;
    }

    @Override
    public User find(UserType type, PhoneNumber phoneNumber) {
        UserDO u = new UserDO();
        u.setType(type.toString());
        u.setPhoneNumber(phoneNumber.getValue());

        return doSelectAndAssembler(u);
    }

    @Override
    public User findOrException(UserType type, PhoneNumber phoneNumber) {
        User user = this.find(type, phoneNumber);
        if (user == null) {
            throw new EntityNullException("User is null, find by PhoneNumber: " + phoneNumber.getValue());
        }
        return user;
    }

    private User doSelectAndAssembler(UserDO u){
        UserDO userDO = userDao.select(u);
        if (userDO != null) {
            return userDOAssembler.toUser(userDO);
        }

        return null;
    }
}
