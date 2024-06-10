package com.coderhack.leaderboard.entity;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUserCreation() {
        User user = new User(1, "user1");

        assertEquals(1, user.getUserId());
        assertEquals("user1", user.getUserName());
        assertEquals(0, user.getScore());
        assertTrue(user.getBadges().isEmpty());
    }

    @Test
    void testSetScore() {
        User user = new User(1, "user1");
        user.setScore(25);

        assertEquals(25, user.getScore());
        assertTrue(user.getBadges().contains("Code Ninja"));

        user.setScore(45);
        assertTrue(user.getBadges().contains("Code Champ"));
        assertFalse(user.getBadges().contains("Code Ninja"));

        user.setScore(75);
        assertTrue(user.getBadges().contains("Code Master"));
        assertFalse(user.getBadges().contains("Code Champ"));
    }

    @Test
    void testSetBadges() {
        User user = new User(1, "user1");
        HashSet<String> badges = new HashSet<>();
        badges.add("Code Ninja");
        user.setBadges(badges);

        assertEquals(1, user.getBadges().size());
        assertTrue(user.getBadges().contains("Code Ninja"));
    }
}
