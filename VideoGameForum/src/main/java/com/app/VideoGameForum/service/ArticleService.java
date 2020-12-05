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

@Service
public class ArticleService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public HashMap<String, Object> getArticleInfoList() {
        String sql = "SELECT * FROM articles ORDER BY date_created DESC";
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
}
