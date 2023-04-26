package com.fishep.user.type;

import com.fishep.common.exception.AppException;
import com.fishep.common.exception.ValidateException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserNameTest {
    @Test
    void testNew() {
        assertDoesNotThrow(() -> {
            new UserName("fly");
            new UserName("fly.fei");
        });

        assertThrows(ValidateException.class, () -> {
            new UserName(".fly");
        });
        assertThrows(AppException.class, () -> {
            new UserName("fly.");
        });
    }

    @Test
    void testEquals() {
        UserName name1 = new UserName("fly");
        UserName name2 = new UserName("fly");
        UserName name3 = new UserName("fly.fei");

        assertTrue(name1.equals(name2));
        assertFalse(name1.equals(name3));
    }

    @Test
    void testEquals1() {
        UserName name = new UserName("fly");

        assertTrue(name.equals("fly"));
        assertFalse(name.equals("fly.fei"));
    }
}