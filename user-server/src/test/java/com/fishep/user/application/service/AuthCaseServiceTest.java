package com.fishep.user.application.service;

import com.fishep.common.exception.ServiceException;
import com.fishep.user.application.dto.LoginDTO;
import com.fishep.user.type.UserType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class AuthCaseServiceTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    AuthCaseService authCaseService;

    @BeforeEach
    public void setUp() {
        String key1 = "CODE_" + UserType.ADMIN + "_1";
        String key2 = "CODE_" + UserType.CUSTOMER + "_1";

        redisTemplate.opsForValue().set(key1, "1234", 600, TimeUnit.SECONDS);
        redisTemplate.opsForValue().set(key2, "1234", 600, TimeUnit.SECONDS);
    }

    @Test
    void login() {
        // login by password
        assertNotNull(authCaseService.adminLoginErp(new LoginDTO("root", "12345678")));
        assertThrows(ServiceException.class, () -> {
            authCaseService.adminLoginErp(new LoginDTO("root", "12345678888"));
        });

        assertNotNull(authCaseService.adminLoginErp(new LoginDTO("root@email.com", "12345678")));
        assertThrows(ServiceException.class, () -> {
            authCaseService.adminLoginErp(new LoginDTO("root@email.com", "12345678888"));
        });

        assertNotNull(authCaseService.adminLoginErp(new LoginDTO("16888888888", "12345678")));
        assertThrows(ServiceException.class, () -> {
            authCaseService.adminLoginErp(new LoginDTO("16888888888", "12345678888"));
        });

        // login by code
        assertNotNull(authCaseService.adminLoginErp(new LoginDTO("root", "1234")));
        assertThrows(ServiceException.class, () -> {
            authCaseService.adminLoginErp(new LoginDTO("root", "4321"));
        });

        assertNotNull(authCaseService.adminLoginErp(new LoginDTO("root@email.com", "1234")));
        assertThrows(ServiceException.class, () -> {
            authCaseService.adminLoginErp(new LoginDTO("root@email.com", "4321"));
        });

        assertNotNull(authCaseService.adminLoginErp(new LoginDTO("16888888888", "1234")));
        assertThrows(ServiceException.class, () -> {
            authCaseService.adminLoginErp(new LoginDTO("16888888888", "4321"));
        });
    }
}