package com.example.NewsAgregrattorWithSpringBoot.NewsAggregartor.Controller;

import com.example.NewsAgregrattorWithSpringBoot.NewsAggregartor.Dto.NewsArticleResponse;
import com.example.NewsAgregrattorWithSpringBoot.NewsAggregartor.Model.NewsArticle;
import com.example.NewsAgregrattorWithSpringBoot.NewsAggregartor.Service.NewsService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    // Add article (optionally for a user)
    @PostMapping
    public ResponseEntity<NewsArticleResponse> createArticle(@RequestParam(required = false) String username,
                                                             @RequestBody NewsArticle payload) {
        NewsArticleResponse r = newsService.saveArticleForUser(username, payload);
        return ResponseEntity.ok(r);
    }

    // Get all stored articles
    @GetMapping
    public ResponseEntity<List<NewsArticleResponse>> listByLoggedInUser(Authentication auth) {
        return ResponseEntity.ok(newsService.getArticlesByUserPreferences(auth.name()));
    }


    // Get articles matching user's preferences
    @GetMapping("/by-user")
    public ResponseEntity<List<NewsArticleResponse>> byUserPreferences(@RequestParam String username) {
        return ResponseEntity.ok(newsService.getArticlesByUserPreferences(username));
    }

    @PostMapping("/{id}/read")
    public ResponseEntity<NewsArticleResponse> markRead(@PathVariable Long id) {
        return ResponseEntity.ok(newsService.markRead(id));
    }

    @PostMapping("/{id}/favorite")
    public ResponseEntity<NewsArticleResponse> markFavorite(@PathVariable Long id) {
        return ResponseEntity.ok(newsService.markFavorite(id));
    }
}

