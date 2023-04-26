package com.fishep.user.application.service;

import com.fishep.common.exception.EntityNullException;
import com.fishep.common.exception.ServiceException;
import com.fishep.user.application.dto.LoginDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class AuthCaseServiceTest {

    @Autowired
    AuthCaseService authCaseService;

    @Test
    void login() {
        // login by password
        assertNotNull(authCaseService.login(new LoginDTO("test", "12345678")));
        assertThrows(ServiceException.class, () -> {authCaseService.login(new LoginDTO("test", "12345678888"));});

        assertNotNull(authCaseService.login(new LoginDTO("test@email.com", "12345678")));
        assertThrows(ServiceException.class, () -> {authCaseService.login(new LoginDTO("test@email.com", "12345678888"));});

        assertThrows(EntityNullException.class, () -> {authCaseService.login(new LoginDTO("17000000000", "12345678"));});

        // login by code
        assertNotNull(authCaseService.login(new LoginDTO("test", "1234")));
        assertThrows(ServiceException.class, () -> {authCaseService.login(new LoginDTO("test", "4321"));});

        assertNotNull(authCaseService.login(new LoginDTO("test@email.com", "1234")));
        assertThrows(ServiceException.class, () -> {authCaseService.login(new LoginDTO("test@email.com", "4321"));});

        assertThrows(EntityNullException.class, () -> {authCaseService.login(new LoginDTO("17000000000", "1234"));});
    }
}