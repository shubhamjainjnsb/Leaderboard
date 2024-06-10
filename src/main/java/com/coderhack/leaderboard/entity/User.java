package com.coderhack.leaderboard.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Document(collection = "userss")
public class User {
    @Id
    private long userId;
    private String userName;
    private int score;
    private Set<String> badges;

    // Constructors
    public User() {
        this.score = 0;
        this.badges = new HashSet<>();
    }

    public User(long userId, String userName) {
        this.userId = userId;
        this.userName = userName;
        this.score = 0;
        this.badges = new HashSet<>();
    }

    // Getters and Setters
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
        updateBadges();
    }

    public Set<String> getBadges() {
        return badges;
    }

    public void setBadges(Set<String> badges) {
        this.badges = badges;
    }

    private void updateBadges() {
        this.badges.clear();
        if (score >= 1 && score < 30) {
            this.badges.add("Code Ninja");
        } else if (score >= 30 && score < 60) {
            this.badges.add("Code Champ");
        } else if (score >= 60 && score <= 100) {
            this.badges.add("Code Master");
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", score=" + score +
                ", badges=" + badges +
                '}';
    }
}
