package com.fishep.common.type;

import com.fishep.common.exception.AppException;
import com.fishep.common.exception.ValidateException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneNumberTest {

    @Test
    void testNew() {
        assertDoesNotThrow(() -> {
            new PhoneNumber("17000000000");
        });

        assertThrows(ValidateException.class, () -> {
            new PhoneNumber("1700");
        });
        assertThrows(AppException.class, () -> {
            new PhoneNumber("aaa");
        });
    }

    @Test
    void testEquals() {
        PhoneNumber phoneNumber1 = new PhoneNumber("17000000001");
        PhoneNumber phoneNumber2 = new PhoneNumber("17000000001");
        PhoneNumber phoneNumber3 = new PhoneNumber("17000000003");

        assertTrue(phoneNumber1.equals(phoneNumber2));
        assertFalse(phoneNumber1.equals(phoneNumber3));
    }

    @Test
    void testEquals1() {
        PhoneNumber phoneNumber1 = new PhoneNumber("17000000001");

        assertTrue(phoneNumber1.equals("17000000001"));
        assertFalse(phoneNumber1.equals("17000000002"));
    }
}