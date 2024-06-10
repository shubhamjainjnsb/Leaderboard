package com.coderhack.leaderboard.service;

import com.coderhack.leaderboard.entity.User;
import com.coderhack.leaderboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public List<User> getUsers() {
        return repository.findAll()
                .stream()
                .sorted((u1, u2) -> Integer.compare(u2.getScore(), u1.getScore()))
                .collect(Collectors.toList());
    }

    @Override
    public User getUser(long userId) {
        return repository.findById(userId).orElse(null);
    }

    @Override
    public User addUser(User user) {
        if (user.getUserId() == 0 || user.getUserName() == null || user.getUserName().isEmpty()) {
            throw new IllegalArgumentException("User ID and Username must be provided.");
        }
        user.setScore(0);
        user.setBadges(new HashSet<>());
        return repository.save(user);
    }

    @Override
    public User updateUser(long userId, User user) {
        if (repository.existsById(userId)) {
            User existingUser = repository.findById(userId).get();
            existingUser.setScore(user.getScore());
            return repository.save(existingUser);
        } else {
            throw new UserNotFoundException("User not found with id: " + userId);
        }
    }

    @Override
    public User deleteUser(long userId) {
        User user = repository.findById(userId).orElse(null);
        if (user != null) {
            repository.delete(user);
        } else {
            throw new UserNotFoundException("User not found with id: " + userId);
        }
        return user;
    }

    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }
}
