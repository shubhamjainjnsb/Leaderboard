package com.coderhack.leaderboard.repository;

import com.coderhack.leaderboard.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void testSaveAndFindUser() {
        User user = new User(1, "user1");
        userRepository.save(user);

        User foundUser = userRepository.findById(1L).orElse(null);

        assertNotNull(foundUser);
        assertEquals("user1", foundUser.getUserName());
    }

    @Test
    void testFindAllUsers() {
        User user1 = new User(1, "user1");
        User user2 = new User(2, "user2");

        userRepository.save(user1);
        userRepository.save(user2);

        List<User> users = userRepository.findAll();

        assertEquals(2, users.size());
    }

    @Test
    void testDeleteUser() {
        User user = new User(1, "user1");
        userRepository.save(user);

        userRepository.delete(user);

        User foundUser = userRepository.findById(1L).orElse(null);

        assertNull(foundUser);
    }
}
