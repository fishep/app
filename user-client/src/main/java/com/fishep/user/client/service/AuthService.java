package com.fishep.user.client.service;

import com.fishep.user.response.auth.TokenCheckResponse;

public interface AuthService {
    TokenCheckResponse check(String guard, String token);
}
