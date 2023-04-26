package com.fishep.user.domain.repository;

import com.fishep.common.exception.EntityNullException;
import com.fishep.common.type.Email;
import com.fishep.common.type.PhoneNumber;
import com.fishep.user.type.UserId;
import com.fishep.user.type.UserName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void save() {
    }

    @Test
    void find() {
        assertNotNull(userRepository.find(new UserId(1l)));
        assertNotNull(userRepository.find(new UserName("test")));
        assertNotNull(userRepository.find(new Email("test@email.com")));

        assertNull(userRepository.find(new UserId(2l)));
        assertNull(userRepository.find(new UserName("test.test")));
        assertNull(userRepository.find(new Email("test.test@email.com")));

        assertNull(userRepository.find(new PhoneNumber("17000000000")));
    }

    @Test
    void findOrException() {
        assertDoesNotThrow(() -> {
            userRepository.findOrException(new UserId(1l));
        });
        assertDoesNotThrow(() -> {
            userRepository.findOrException(new UserName("test"));
        });
        assertDoesNotThrow(() -> {
            userRepository.findOrException(new Email("test@email.com"));
        });

        assertThrows(EntityNullException.class, () -> {
            userRepository.findOrException(new UserId(2l));
        });
        assertThrows(EntityNullException.class, () -> {
            userRepository.findOrException(new UserName("test.test"));
        });
        assertThrows(EntityNullException.class, () -> {
            userRepository.findOrException(new Email("test.test@email.com"));
        });

        assertThrows(EntityNullException.class, () -> {
            userRepository.findOrException(new PhoneNumber("17000000000"));
        });
    }
}