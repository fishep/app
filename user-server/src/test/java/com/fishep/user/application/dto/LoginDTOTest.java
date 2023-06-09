package com.fishep.user.application.dto;

import com.fishep.common.exception.NullException;
import com.fishep.common.exception.TypeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginDTOTest {

    @Test
    void testNew() {
        assertThrows(NullException.class, () -> {new LoginDTO(null, "aaa");});
        assertThrows(NullException.class, () -> {new LoginDTO("", "aaa");});
        assertThrows(NullException.class, () -> {new LoginDTO("aaa", null);});
        assertThrows(NullException.class, () -> {new LoginDTO("aaa", "");});

        assertThrows(TypeException.class, () -> {new LoginDTO(".", "password");});
        assertThrows(TypeException.class, () -> {new LoginDTO("email@", "password");});
        assertThrows(TypeException.class, () -> {new LoginDTO("1700000000000", "password");});

        assertDoesNotThrow(()->{new LoginDTO("fly", "password");});
        assertDoesNotThrow(()->{new LoginDTO("fly@test", "password");});
        assertDoesNotThrow(()->{new LoginDTO("17000000000", "password");});
    }

    @Test
    void getIdentityType() {
        assertEquals(LoginDTO.IdentityType.UserName, new LoginDTO("fly", "password").getIdentityType());
        assertEquals(LoginDTO.IdentityType.Email, new LoginDTO("fly@test", "password").getIdentityType());
        assertEquals(LoginDTO.IdentityType.PhoneNumber, new LoginDTO("17000000000", "password").getIdentityType());

        assertEquals(LoginDTO.IdentityType.UserName, new LoginDTO("fly", "1234").getIdentityType());
        assertEquals(LoginDTO.IdentityType.Email, new LoginDTO("fly@test", "1234").getIdentityType());
        assertEquals(LoginDTO.IdentityType.PhoneNumber, new LoginDTO("17000000000", "1234").getIdentityType());
    }

    @Test
    void getTokenType() {
        assertEquals(LoginDTO.TokenType.Password, new LoginDTO("fly", "password").getTokenType());
        assertEquals(LoginDTO.TokenType.Password, new LoginDTO("fly@test", "password").getTokenType());
        assertEquals(LoginDTO.TokenType.Password, new LoginDTO("17000000000", "password").getTokenType());

        assertEquals(LoginDTO.TokenType.Code, new LoginDTO("fly", "1234").getTokenType());
        assertEquals(LoginDTO.TokenType.Code, new LoginDTO("fly@test", "1234").getTokenType());
        assertEquals(LoginDTO.TokenType.Code, new LoginDTO("17000000000", "1234").getTokenType());
    }
}