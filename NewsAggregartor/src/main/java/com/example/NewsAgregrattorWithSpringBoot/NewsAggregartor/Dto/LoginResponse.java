package com.example.NewsAgregrattorWithSpringBoot.NewsAggregartor.Dto;

public class LoginResponse {
    private boolean success;
    private String token;

    public LoginResponse() {}

    // Constructor for token-based login success
    public LoginResponse(boolean success, String token) {
        this.success = success;
        this.token = token;
    }

    // Constructor for error messages
    public LoginResponse(boolean success, String tokenOrMessage, boolean isToken) {
        this.success = success;
        if (isToken) {
            this.token = tokenOrMessage;
        } else {
            this.token = null; // no token
        }
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
