package com.example.NewsAgregrattorWithSpringBoot.NewsAggregartor.Dto;

public class NewsArticleResponse {
    private Long id;
    private String title;
    private String description;
    private String url;
    private boolean read;
    private boolean favorite;

    public NewsArticleResponse() {}

    public NewsArticleResponse(Long id, String title, String description, String url, boolean read, boolean favorite) {
        this.id = id; this.title = title; this.description = description; this.url = url;
        this.read = read; this.favorite = favorite;
    }

    // getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public boolean isRead() { return read; }
    public void setRead(boolean read) { this.read = read; }
    public boolean isFavorite() { return favorite; }
    public void setFavorite(boolean favorite) { this.favorite = favorite; }
}
