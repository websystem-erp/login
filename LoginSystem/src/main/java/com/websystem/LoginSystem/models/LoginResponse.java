package com.websystem.LoginSystem.models;

public class LoginResponse {
    private String message;
    private String token;
    private UserData userData; // Include user data

    public LoginResponse(String message, String token, UserData userData) {
        this.message = message;
        this.token = token;
        this.userData = userData;
    }

    public LoginResponse(String message, String token) {
        this.message = message;
        this.token = token;
    }

    // Getters and Setters

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }
}
