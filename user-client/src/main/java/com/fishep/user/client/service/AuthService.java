package com.fishep.user.client.service;

public interface AuthService {
    Long check(String guard, String token);
}
