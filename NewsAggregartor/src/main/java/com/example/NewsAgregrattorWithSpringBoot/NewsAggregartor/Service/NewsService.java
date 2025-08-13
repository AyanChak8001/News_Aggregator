package com.example.NewsAgregrattorWithSpringBoot.NewsAggregartor.Service;

import com.example.NewsAgregrattorWithSpringBoot.NewsAggregartor.Dto.NewsArticleResponse;
import com.example.NewsAgregrattorWithSpringBoot.NewsAggregartor.Model.NewsArticle;
import com.example.NewsAgregrattorWithSpringBoot.NewsAggregartor.Model.User;
import com.example.NewsAgregrattorWithSpringBoot.NewsAggregartor.Repository.ArticleRepository;
import com.example.NewsAgregrattorWithSpringBoot.NewsAggregartor.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class NewsService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    public NewsArticleResponse saveArticleForUser(String username, NewsArticle article) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user != null) {
            article.setUser(user);
        }
        NewsArticle saved = articleRepository.save(article);
        return toDto(saved);
    }

    public List<NewsArticleResponse> getAllArticles() {
        return articleRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<NewsArticleResponse> getArticlesByUserPreferences(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) return List.of();
        var prefs = user.getPreferences();
        if (prefs == null || prefs.isEmpty()) return getAllArticles();
        // simple filter by title/description containing any pref (demo)
        return articleRepository.findAll().stream()
                .filter(a -> {
                    String text = (a.getTitle()==null? "":a.getTitle()) + " " + (a.getDescription()==null?"":a.getDescription());
                    for (String p : prefs) {
                        if (text.toLowerCase().contains(p.toLowerCase())) return true;
                    }
                    return false;
                })
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public NewsArticleResponse markRead(Long articleId) {
        NewsArticle a = articleRepository.findById(articleId).orElseThrow();
        a.setRead(true);
        articleRepository.save(a);
        return toDto(a);
    }

    public NewsArticleResponse markFavorite(Long articleId) {
        NewsArticle a = articleRepository.findById(articleId).orElseThrow();
        a.setFavorite(true);
        articleRepository.save(a);
        return toDto(a);
    }

    private NewsArticleResponse toDto(NewsArticle a) {
        return new NewsArticleResponse(a.getId(), a.getTitle(), a.getDescription(), a.getUrl(), a.isRead(), a.isFavorite());
    }
}

