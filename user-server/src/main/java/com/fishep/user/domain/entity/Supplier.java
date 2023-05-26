package com.fishep.user.domain.entity;

import com.fishep.user.type.UserId;
import com.fishep.user.type.UserName;

/**
 * @Author Fly.Fei
 * @Date 2023/5/24 12:27
 * @Desc
 **/
public class Supplier extends User {
    public Supplier(UserId id, UserName name) {
        super(id, name);
    }
}
