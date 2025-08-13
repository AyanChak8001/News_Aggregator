package com.example.NewsAgregrattorWithSpringBoot.NewsAggregartor.Dto;


import jakarta.validation.constraints.NotBlank;
import java.util.List;

public class RegisterRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private String email;
    private List<String> preferences;

    public RegisterRequest() {}

    // getters/setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public List<String> getPreferences() { return preferences; }
    public void setPreferences(List<String> preferences) { this.preferences = preferences; }
}
