package com.fishep.user.domain.entity;

import com.fishep.user.type.UserId;
import com.fishep.user.type.UserName;

/**
 * @Author Fly.Fei
 * @Date 2023/5/24 12:26
 * @Desc
 **/
public class Customer extends User {
    public Customer(UserId id, UserName name) {
        super(id, name);
    }
}
