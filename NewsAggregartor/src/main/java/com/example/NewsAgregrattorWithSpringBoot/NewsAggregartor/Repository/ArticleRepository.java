package com.example.NewsAgregrattorWithSpringBoot.NewsAggregartor.Repository;


import com.example.NewsAgregrattorWithSpringBoot.NewsAggregartor.Model.NewsArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<NewsArticle, Long> {
    Optional<NewsArticle> findByUrl(String url);
    List<NewsArticle> findByUserId(Long userId);
}