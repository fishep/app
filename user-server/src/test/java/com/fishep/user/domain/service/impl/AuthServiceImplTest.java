package com.fishep.user.domain.service.impl;

import com.fishep.common.exception.ClassTypeException;
import com.fishep.common.type.Email;
import com.fishep.user.domain.entity.Code;
import com.fishep.user.domain.entity.Password;
import com.fishep.user.domain.entity.Token;
import com.fishep.user.domain.entity.User;
import com.fishep.user.domain.service.AuthService;
import com.fishep.user.type.UserId;
import com.fishep.user.type.UserName;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class AuthServiceImplTest {

    @Test
    void check() {
        AuthService authService = new AuthServiceImpl();

        User user = new User(new UserId(1l), new UserName("fly"), new Email("fly@test"), Instant.now(), Instant.now());
        Token password1 = new Password("1234");
        Token password2 = new Password("1234");
        Token password3 = new Password("12345555");
        Token code1 = new Code("1234");
        Token code2 = new Code("1234");
        Token code3 = new Code("1234111111");

        assertThrows(IllegalArgumentException.class, () -> {
            authService.check(user, password1);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            authService.check(user, code1);
        });

        user.holdToken(password1);
        assertTrue(authService.check(user, password2));
        assertFalse(authService.check(user, password3));

        user.holdToken(code1);
        assertTrue(authService.check(user, code2));
        assertFalse(authService.check(user, code3));

        assertThrows(ClassTypeException.class, () -> {
            authService.check(user, password1);
        });
    }
}