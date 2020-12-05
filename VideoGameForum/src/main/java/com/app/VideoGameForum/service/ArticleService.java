package com.app.VideoGameForum.service;

import com.app.VideoGameForum.model.Article;
import com.app.VideoGameForum.util.ArticleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class ArticleService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public HashMap<String, Object> getEnabledArticles() {
        String sql = "SELECT article_id, username, title, date_created FROM articles WHERE enabled = true ORDER BY date_created DESC;";
        HashMap<String, Object> response = new HashMap<>();

        try {
            List<Article> listArticles = jdbcTemplate.query(sql, new ArticleMapper());
            response.put("articles", listArticles);
            log.info("Retrieved a list of articles on ({}).", new Date());
        } catch (DataAccessException exception) {
            exception.printStackTrace();
            log.info("Failed to retrieve list of articles on ({}).", new Date());
            response.put("status", "Error");
            response.put("message", "Failed to retrieve list of articles.");
        }

        return response;
    }

    public HashMap<String, Object> getAllArticles() {
        String sql = "SELECT article_id, username, title, date_created FROMm articles ORDER BY date_Created DESC;";
        HashMap<String, Object> response = new HashMap<>();

        try {
            List<Article> listArticles = jdbcTemplate.query(sql, new ArticleMapper());
            response.put("articles", listArticles);
            log.info("Retrieved a list of articles on ({}).", new Date());
        } catch (DataAccessException exception) {
            exception.printStackTrace();
            log.info("Failed to retrieve list of articles on ({}).", new Date());
            response.put("status", "Error");
            response.put("message", "Failed to retrieve list of articles.");
        }

        return response;
    }

    public HashMap<String, Object> disableArticle(UUID article_id) {
        String sql = "UPDATE articles SET enabled = false WHERE article_id = ?;";
        HashMap<String, Object> response = new HashMap<>();

        try {
            jdbcTemplate.update(sql, article_id);
            response.put("success", true);
            response.put("message", "Successfully disabled article.");
            log.info("Successfully disabled article {} on {}.", article_id, new Date());
        } catch (DataAccessException exception) {
            exception.printStackTrace();
            log.info("Failed to disable article {} on {}.", article_id, new Date());
            response.put("success", false);
            response.put("message", "Failed to disable article.");
        }

        return response;
    }

    public HashMap<String, Object> enableArticle(UUID article_id) {
        String sql = "UPDATE articles SET enabled = true WHERE article_id = ?;";
        HashMap<String, Object> response = new HashMap<>();

        try {
            jdbcTemplate.update(sql, article_id);
            response.put("success", true);
            response.put("message", "Successfully enabled article.");
            log.info("Successfully enabled article {} on {}.", article_id, new Date());
        } catch (DataAccessException exception) {
            exception.printStackTrace();
            log.info("Failed to enable article {} on {}.", article_id, new Date());
            response.put("success", false);
            response.put("message", "Failed to enable article.");
        }

        return response;
    }

}
