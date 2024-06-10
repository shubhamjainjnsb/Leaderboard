package com.coderhack.leaderboard.service;

import com.coderhack.leaderboard.entity.User;
import java.util.List;

public interface UserService {
    List<User> getUsers();
    User getUser(long userId);
    User addUser(User user);
    User updateUser(long userId, User user);
    User deleteUser(long userId);
}
