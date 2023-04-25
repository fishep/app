package com.fishep.common.type;

import com.fishep.common.exception.ValidateEmailException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmailTest {

    @Test
    void testNew() {
        Assertions.assertDoesNotThrow(() -> {
            new Email("fly.fei@test.com");
        });

        Assertions.assertThrows(ValidateEmailException.class, () -> {
            new Email("fly.fei");
        });
        Assertions.assertThrows(ValidateEmailException.class, () -> {
            new Email("@test");
        });
        Assertions.assertThrows(ValidateEmailException.class, () -> {
            new Email("fly@");
        });
    }

    @Test
    void testEquals() {
        Email email1 = new Email("fly.fei@test.com");
        Email email2 = new Email("fly.fei@test.com");
        Email email3 = new Email("fly@test.com");

        assertTrue(email1.equals(email2));
        assertTrue(email1.equals("fly.fei@test.com"));
        assertFalse(email1.equals(email3));
        assertFalse(email1.equals("fly@test.com"));
    }
}