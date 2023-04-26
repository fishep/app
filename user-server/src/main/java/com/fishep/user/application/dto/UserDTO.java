package com.fishep.user.application.dto;

import lombok.Data;

@Data
public class UserDTO {
    public Long id;

    public String name;

    public String email;

    public String token;
}
