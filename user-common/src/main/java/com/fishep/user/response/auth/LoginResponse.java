package com.fishep.user.response.auth;

import lombok.Data;

@Data
public class LoginResponse {
    public String identity;

    public String token;
}
