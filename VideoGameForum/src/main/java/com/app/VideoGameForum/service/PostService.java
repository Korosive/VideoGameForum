package com.app.VideoGameForum.service;

import com.app.VideoGameForum.model.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

@Service
public class PostService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public HashMap<String, Object> createPost(Post new_post) {
        String sql = "INSERT INTO posts (post_id, username, title, description, date_created, last_updated) VALUES (?, ?, ?, ?, ?, ?);";
        HashMap<String, Object> response = new HashMap<>();
        UUID post_id = UUID.randomUUID();
        String username = new_post.getUsername();
        String title = new_post.getTitle();
        String description = new_post.getDescription();
        Date date_created = new Date();
        Date last_updated = new Date();

        try {
            jdbcTemplate.update(sql, post_id, username, title, description, date_created, last_updated);
            response.put("success", true);
            response.put("message", "Successfully created post.");
            log.info("Successfully created post at {}.", new Date());
        } catch (DataAccessException exception) {
            exception.printStackTrace();
            response.put("success", false);
            response.put("message", "Failed to create post.");
            log.info("Failed to create post at {}.", new Date());
        }

        return response;
    }
}
