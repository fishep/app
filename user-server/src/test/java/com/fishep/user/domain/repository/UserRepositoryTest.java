package com.fishep.user.domain.repository;

import com.fishep.common.exception.EntityNullException;
import com.fishep.common.type.Email;
import com.fishep.common.type.PhoneNumber;
import com.fishep.user.type.UserId;
import com.fishep.user.type.UserName;
import com.fishep.user.type.UserType;
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
        assertNotNull(userRepository.find(UserType.ADMIN, new UserId(1l)));
        assertNotNull(userRepository.find(UserType.ADMIN, new UserName("test")));
        assertNotNull(userRepository.find(UserType.ADMIN, new Email("test@email.com")));
        assertNotNull(userRepository.find(UserType.ADMIN, new PhoneNumber("16888888888")));

        assertNull(userRepository.find(UserType.ADMIN, new UserId(2l)));
        assertNull(userRepository.find(UserType.ADMIN, new UserName("test.test")));
        assertNull(userRepository.find(UserType.ADMIN, new Email("test.test@email.com")));
        assertNull(userRepository.find(UserType.ADMIN, new PhoneNumber("17000000000")));
    }

    @Test
    void findOrException() {
        assertDoesNotThrow(() -> {
            userRepository.findOrException(UserType.ADMIN, new UserId(1l));
        });
        assertDoesNotThrow(() -> {
            userRepository.findOrException(UserType.ADMIN, new UserName("test"));
        });
        assertDoesNotThrow(() -> {
            userRepository.findOrException(UserType.ADMIN, new Email("test@email.com"));
        });
        assertDoesNotThrow(() -> {
            userRepository.findOrException(UserType.ADMIN, new PhoneNumber("16888888888"));
        });

        assertThrows(EntityNullException.class, () -> {
            userRepository.findOrException(UserType.ADMIN, new UserId(2l));
        });
        assertThrows(EntityNullException.class, () -> {
            userRepository.findOrException(UserType.ADMIN, new UserName("test.test"));
        });
        assertThrows(EntityNullException.class, () -> {
            userRepository.findOrException(UserType.ADMIN, new Email("test.test@email.com"));
        });
        assertThrows(EntityNullException.class, () -> {
            userRepository.findOrException(UserType.ADMIN, new PhoneNumber("17000000000"));
        });
    }
}