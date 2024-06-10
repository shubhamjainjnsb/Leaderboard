package com.coderhack.leaderboard.service;

import com.coderhack.leaderboard.entity.User;
import com.coderhack.leaderboard.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUsers() {
        User user1 = new User(1, "user1");
        user1.setScore(50);
        User user2 = new User(2, "user2");
        user2.setScore(70);

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<User> users = userService.getUsers();

        assertEquals(2, users.size());
        assertEquals("user2", users.get(0).getUserName());
        assertEquals("user1", users.get(1).getUserName());
    }

    @Test
    void testGetUser() {
        User user = new User(1, "user1");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User foundUser = userService.getUser(1L);

        assertNotNull(foundUser);
        assertEquals("user1", foundUser.getUserName());
    }

    @Test
    void testAddUser() {
        User user = new User(1, "user1");

        when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.addUser(user);

        assertNotNull(createdUser);
        assertEquals(0, createdUser.getScore());
        assertTrue(createdUser.getBadges().isEmpty());
    }

    @Test
    void testUpdateUser() {
        User existingUser = new User(1, "user1");
        existingUser.setScore(50);

        User updatedUserDetails = new User();
        updatedUserDetails.setScore(60);

        when(userRepository.existsById(1L)).thenReturn(true);
        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(existingUser);

        User updatedUser = userService.updateUser(1L, updatedUserDetails);

        assertNotNull(updatedUser);
        assertEquals(60, updatedUser.getScore());
        assertTrue(updatedUser.getBadges().contains("Code Master"));
    }

    @Test
    void testDeleteUser() {
        User user = new User(1, "user1");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User deletedUser = userService.deleteUser(1L);

        assertNotNull(deletedUser);
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    void testAddUserWithInvalidInput() {
        User user = new User();
        user.setUserName("user1");

        assertThrows(IllegalArgumentException.class, () -> {
            userService.addUser(user);
        });
    }

    @Test
    void testUpdateUserNotFound() {
        User updatedUserDetails = new User();
        updatedUserDetails.setScore(60);

        when(userRepository.existsById(1L)).thenReturn(false);

        assertThrows(UserServiceImpl.UserNotFoundException.class, () -> {
            userService.updateUser(1L, updatedUserDetails);
        });
    }

    @Test
    void testDeleteUserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserServiceImpl.UserNotFoundException.class, () -> {
            userService.deleteUser(1L);
        });
    }
}
