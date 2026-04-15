package com.vsrdev.terminux_review_api.model.entity;

public class User {

    private String username;

    public User(String username) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username invalido");
        }

        this.username = username;
    }

    public String getName() {
        return username;
    }
}