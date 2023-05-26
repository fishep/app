package com.fishep.user.application.service;

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
        assertNotNull(authCaseService.adminLoginErp(new LoginDTO("test", "12345678")));
        assertThrows(ServiceException.class, () -> {authCaseService.adminLoginErp(new LoginDTO("test", "12345678888"));});

        assertNotNull(authCaseService.adminLoginErp(new LoginDTO("test@email.com", "12345678")));
        assertThrows(ServiceException.class, () -> {authCaseService.adminLoginErp(new LoginDTO("test@email.com", "12345678888"));});

        assertNotNull(authCaseService.adminLoginErp(new LoginDTO("16888888888", "12345678")));
        assertThrows(ServiceException.class, () -> {authCaseService.adminLoginErp(new LoginDTO("16888888888", "12345678888"));});

        // login by code
        assertNotNull(authCaseService.adminLoginErp(new LoginDTO("test", "1234")));
        assertThrows(ServiceException.class, () -> {authCaseService.adminLoginErp(new LoginDTO("test", "4321"));});

        assertNotNull(authCaseService.adminLoginErp(new LoginDTO("test@email.com", "1234")));
        assertThrows(ServiceException.class, () -> {authCaseService.adminLoginErp(new LoginDTO("test@email.com", "4321"));});

        assertNotNull(authCaseService.adminLoginErp(new LoginDTO("16888888888", "1234")));
        assertThrows(ServiceException.class, () -> {authCaseService.adminLoginErp(new LoginDTO("16888888888", "4321"));});
    }
}