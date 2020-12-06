package com.app.VideoGameForum.controller;

import com.app.VideoGameForum.model.Article;
import com.app.VideoGameForum.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping(value = "/get/enabled", produces = "application/json")
    public HashMap<String, Object> getEnabledArticles() {
        return articleService.getEnabledArticles();
    }

    @GetMapping(value = "/get/all", produces = "application/json")
    public HashMap<String, Object> getAllArticles() {
        return articleService.getAllArticles();
    }

    @GetMapping(value = "/get/article/{article_id}", produces = "application/json")
    public HashMap<String, Object> getArticleById(@PathVariable UUID article_id) {
        return articleService.getArticleById(article_id);
    }

    @PostMapping(value = "/article/create", consumes = "application/json", produces = "application/json")
    public HashMap<String, Object> createArticle(@RequestBody Article new_article) {
        return articleService.createArticle(new_article);
    }

    @PutMapping(value = "/article/update", consumes = "applications/json", produces = "application/json")
    public HashMap<String, Object> updateArticle(@RequestBody Article update_article) {
        return articleService.updateArticle(update_article);
    }

    @PutMapping(value = "/article/enable/{article_id}", produces = "application/json")
    public HashMap<String, Object> enableArticle(@PathVariable UUID article_id) {
        return articleService.enableArticle(article_id);
    }

    @PutMapping(value = "/article/disable/{article_id}", produces = "application/json")
    public HashMap<String, Object> disableArticle(@PathVariable UUID article_id) {
        return articleService.disableArticle(article_id);
    }
}
