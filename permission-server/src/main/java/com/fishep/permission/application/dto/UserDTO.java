package com.fishep.permission.application.dto;

import com.fishep.common.type.Guard;
import com.fishep.user.type.UserId;
import com.fishep.user.type.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author fly.fei
 * @Date 2023/7/6 10:57
 * @Desc
 **/
@Getter
@AllArgsConstructor
public class UserDTO {

    public UserType userType;

    public UserId userId;

    public Guard guard;

}
