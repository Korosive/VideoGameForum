package com.app.VideoGameForum.service;

import com.app.VideoGameForum.model.Comment;
import com.app.VideoGameForum.util.CommentMapper;
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
public class CommentService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public HashMap<String, Object> createComment(Comment new_comment) {
        String sql = "INSERT INTO comments (comment_id, post_id, username, comment, date_created, last_updated, enabled) VALUES (?, ?, ?, ?, ?, ?, ?)";
        HashMap<String, Object> response = new HashMap<>();
        UUID comment_id = UUID.randomUUID();
        UUID post_id = new_comment.getPost_id();
        String username = new_comment.getUsername();
        String comment = new_comment.getUsername();
        Date date_created = new Date(), last_updated = new Date();

        try {
            jdbcTemplate.update(sql, comment_id, post_id, username, comment, date_created, last_updated, true);
            response.put("success", true);
            response.put("message", "Successfully created comment.");
            log.info("Created comment ({}) for post ({}) at {}.", comment_id, post_id, new Date());
        } catch (DataAccessException exception) {
            exception.printStackTrace();
            response.put("success", false);
            response.put("message", "Failed to create comment.");
            log.info("Failed to create comment for post at {}.", new Date());
        }

        return response;
    }

    public HashMap<String, Object> disableComment(UUID comment_id) {
        String sql = "UPDATE comments SET enabled = false WHERE comment_id = ?;";
        HashMap<String, Object> response = new HashMap<>();

        if (checkCommentEnabled(comment_id)) {
            try {
                jdbcTemplate.update(sql, comment_id);
                response.put("success", true);
                response.put("message", "Successfully disabled comment.");
                log.info("Successfully disabled comment {} on {}.", comment_id, new Date());
            } catch (DataAccessException exception) {
                exception.printStackTrace();
                log.info("Failed to disable comment {} on {}.", comment_id, new Date());
                response.put("success", false);
                response.put("message", "Failed to disable comment.");
            }
        } else {
            response.put("success", false);
            response.put("message", "Comment is already disabled.");
            log.info("Comment already disabled ({}).", new Date());
        }

        return response;
    }

    public HashMap<String, Object> enableComment(UUID comment_id) {
        String sql = "UPDATE comments SET enabled = true WHERE comment_id = ?;";
        HashMap<String, Object> response = new HashMap<>();

        if (checkCommentEnabled(comment_id)) {
            response.put("success", false);
            response.put("message", "Comment is already enabled.");
            log.info("Comment already enabled ({}).", new Date());
        } else {
            try {
                jdbcTemplate.update(sql, comment_id);
                response.put("success", true);
                response.put("message", "Successfully enabled comment.");
                log.info("Successfully enabled comment {} on {}.", comment_id, new Date());
            } catch (DataAccessException exception) {
                exception.printStackTrace();
                log.info("Failed to enable comment {} on {}.", comment_id, new Date());
                response.put("success", false);
                response.put("message", "Failed to enable comment.");
            }
        }

        return response;
    }

    private boolean checkCommentEnabled(UUID comment_id) {
        String sql = "SELECT * FROM comments WHERE comment_id = ?;";
        boolean enabled;

        try {
            Comment comment = jdbcTemplate.queryForObject(sql, new Object[]{comment_id}, new CommentMapper());
            enabled = comment.isEnabled();
        } catch (DataAccessException exception) {
            exception.printStackTrace();
            enabled = false;
        }

        return enabled;
    }

    public HashMap<String, Object> getUserComments(String username) {
        String sql = "SELECT * FROM comments WHERE username = ?;";
        HashMap<String, Object> response = new HashMap<>();

        try {
            List<Comment> listComments = jdbcTemplate.query(sql, new Object[]{username}, new CommentMapper());
            response.put("success", true);
            response.put("comments", listComments);
            log.info("Successfully retrieved a list of users comments at {}.", new Date());
        } catch (DataAccessException exception) {
            exception.printStackTrace();
            response.put("success", false);
            response.put("message", "Failed to retrieve user's comments.");
            log.info("Failed to retrieve a list of users comments at {}.", new Date());
        }

        return response;
    }
}
