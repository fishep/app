package com.fishep.user.client.service.impl;

import com.fishep.user.client.service.AuthService;
import com.fishep.user.response.auth.TokenCheckResponse;

public class AuthServiceDubboImpl implements AuthService {
    @Override
    public TokenCheckResponse check(String guard, String token) {
        return null;
    }
}
