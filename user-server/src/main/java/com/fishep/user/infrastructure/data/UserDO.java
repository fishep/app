package com.fishep.user.infrastructure.data;

import lombok.Data;

@Data
public class UserDO {

    private Long id;

    private String name;

    private String email;

    private String password;

    private Long createdAt;

    private Long updatedAt;

}
