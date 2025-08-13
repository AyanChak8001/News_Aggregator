package com.example.NewsAgregrattorWithSpringBoot.NewsAggregartor.Dto;

import java.util.List;

import java.util.List;

import java.util.List;

public class PreferencesRequest {

    private List<String> userPreferences;

    public PreferencesRequest() {
    }

    public PreferencesRequest(List<String> userPreferences) {
        this.userPreferences = userPreferences;
    }

    public List<String> getUserPreferences() {
        return userPreferences;
    }

    public void setUserPreferences(List<String> userPreferences) {
        this.userPreferences = userPreferences;
    }
}
