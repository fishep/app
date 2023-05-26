package com.fishep.user.infrastructure.assembler;

import com.fishep.common.exception.ClassTypeException;
import com.fishep.common.type.Email;
import com.fishep.common.type.PhoneNumber;
import com.fishep.user.domain.entity.Admin;
import com.fishep.user.domain.entity.Customer;
import com.fishep.user.domain.entity.Supplier;
import com.fishep.user.domain.entity.User;
import com.fishep.user.infrastructure.data.UserDO;
import com.fishep.user.type.UserId;
import com.fishep.user.type.UserName;
import com.fishep.user.type.UserType;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class UserDOAssembler {

    public User toUser(UserDO userDO) {
        User user;
        if (userDO.getType().equals(UserType.ADMIN.toString())) {
            user = new Admin(new UserId(userDO.getId()), new UserName(userDO.getName()));
        } else if (userDO.getType().equals(UserType.CUSTOMER.toString())) {
            user = new Customer(new UserId(userDO.getId()), new UserName(userDO.getName()));
        } else if (userDO.getType().equals(UserType.SUPPLIER.toString())) {
            user = new Supplier(new UserId(userDO.getId()), new UserName(userDO.getName()));
        } else {
            throw new ClassTypeException("Unsupported UserType, userDO.getType(): " + userDO.getType());
        }
        if (userDO.getEmail() != null) {
            user.setEmail(new Email(userDO.getEmail()));
        }
        if (userDO.getPhoneNumber() != null) {
            user.setPhoneNumber(new PhoneNumber(userDO.getPhoneNumber()));
        }
        user.setCreatedAt(Instant.ofEpochMilli(userDO.getCreatedAt()));
        user.setUpdatedAt(Instant.ofEpochMilli(userDO.getUpdatedAt()));

        return user;
    }

    public UserDO toUserDO(User user) {
        UserDO userDO = new UserDO();

        if (user instanceof Admin) {
            userDO.setType(UserType.ADMIN.toString());
        } else if (user instanceof Customer) {
            userDO.setType(UserType.CUSTOMER.toString());
        } else if (user instanceof Supplier) {
            userDO.setType(UserType.SUPPLIER.toString());
        }
        userDO.setId(user.getId().getValue());
        userDO.setName(user.getName().getValue());
        userDO.setEmail(user.getEmail() != null ? user.getEmail().getValue() : null);
        userDO.setPhoneNumber(user.getPhoneNumber() != null ? user.getPhoneNumber().getValue() : null);
        userDO.setPassword(user.getToken() != null ? user.getToken().getCheckValue() : null);
        userDO.setCreatedAt(user.getCreatedAt() != null ? user.getCreatedAt().toEpochMilli() : null);
        userDO.setUpdatedAt(user.getUpdatedAt() != null ? user.getUpdatedAt().toEpochMilli() : null);

        return userDO;
    }

}
