package com.example.applause;

public class User {
    private String username;
    private int speed;

    public User(String username, int speed) {
        this.username = username;
        this.speed = speed;
    }

    public String getUsername() {
        return username;
    }

    public int getSpeed() {
        return speed;
    }
}
