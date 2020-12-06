package com.app.VideoGameForum.service;

import com.app.VideoGameForum.model.Comment;
import com.app.VideoGameForum.model.Post;
import com.app.VideoGameForum.util.CommentMapper;
import com.app.VideoGameForum.util.PostMapper;
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
public class PostService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public HashMap<String, Object> createPost(Post new_post) {
        String sql = "INSERT INTO posts (post_id, username, title, description, date_created, last_updated, enabled) VALUES (?, ?, ?, ?, ?, ?, ?);";
        HashMap<String, Object> response = new HashMap<>();
        UUID post_id = UUID.randomUUID();
        String username = new_post.getUsername();
        String title = new_post.getTitle();
        String description = new_post.getDescription();
        Date date_created = new Date();
        Date last_updated = new Date();

        try {
            jdbcTemplate.update(sql, post_id, username, title, description, date_created, last_updated, true);
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
    public HashMap<String, Object> getPostThread(UUID post_id) {
        String sql_post = "SELECT * FROM posts WHERE post_id = ?;";
        String sql_comments = "SELECT * FROM comments WHERE post_id = ?;";
        HashMap<String, Object> response = new HashMap<>();

        try {
            Post post = jdbcTemplate.queryForObject(sql_post, new Object[]{post_id}, new PostMapper());
            log.info("Retrieved post: {} at {}.", post_id, new Date());
            List<Comment> listComments = jdbcTemplate.query(sql_comments, new Object[]{post_id}, new CommentMapper());
            log.info("Retrieved comments for {} at {}.", post_id, new Date());
            response.put("post", post);
            response.put("comments", listComments);
        } catch (DataAccessException exception) {
            exception.printStackTrace();
            response.put("success", false);
            response.put("message", "Failed to retrieve post thread.");
            log.info("Failed to get post thread {} at {}.", post_id, new Date());
        }

        return response;
    }

    public HashMap<String, Object> getLivePosts() {
        String sql = "SELECT * FROM posts WHERE enabled = true;";
        HashMap<String, Object> response = new HashMap<>();

        try {
            List<Post> listPost = jdbcTemplate.query(sql, new PostMapper());
            response.put("posts", listPost);
            log.info("Retrieved list of post at {}.", new Date());
        } catch (DataAccessException exception) {
            exception.printStackTrace();
            response.put("success", false);
            response.put("message", "Failed to retrieve list of posts");
            log.info("Failed to get list of posts at {}.", new Date());
        }

        return response;
    }

    public HashMap<String, Object> getAllPosts() {
        String sql = "SELECT * FROM posts;";
        HashMap<String, Object> response = new HashMap<>();

        try {
            List<Post> listPosts = jdbcTemplate.query(sql, new PostMapper());
            response.put("posts", listPosts);
            log.info("Successfully retrieved all posts at {}.", new Date());
        } catch (DataAccessException exception) {
            exception.printStackTrace();
            response.put("success", false);
            response.put("message", "Failed to retrieve all posts.");
            log.info("Failed to retrieve all posts at {}.", new Date());
        }

        return response;
    }

    public HashMap<String, Object> disablePost(UUID post_id) {
        String sql = "UPDATE posts SET enabled = false WHERE post_id = ?;";
        HashMap<String, Object> response = new HashMap<>();

        if (checkPostEnabled(post_id)) {
            try {
                jdbcTemplate.update(sql, post_id);
                response.put("success", true);
                response.put("message", "Successfully disabled post.");
                log.info("Successfully disabled post {} at {}.", post_id, new Date());
            } catch (DataAccessException exception) {
                exception.printStackTrace();
                response.put("success", false);
                response.put("message", "Failed to disable post.");
            }
        } else {
            response.put("success", false);
            response.put("message", "Post is already disabled.");
            log.info("Post is already disabled at {}.", new Date());
        }

        return response;
    }

    public HashMap<String, Object> enablePost(UUID post_id) {
        String sql = "UPDATE posts SET enabled = true WHERE post_id = ?;";
        HashMap<String, Object> response = new HashMap<>();

        if (checkPostEnabled(post_id)) {
            response.put("success", false);
            response.put("message", "Post is already enabled.");
            log.info("Post is already enabled at {}.", new Date());
        } else {
            try {
                jdbcTemplate.update(sql, post_id);
                response.put("success", true);
                response.put("message", "Successfully enabled post.");
                log.info("Successfully enabled post {} at {}.", post_id, new Date());
            } catch (DataAccessException exception) {
                exception.printStackTrace();
                response.put("success", false);
                response.put("message", "Failed to enabled post.");
                log.info("Failed to enable post {} at {}.", post_id, new Date());
            }
        }

        return response;
    }

    private boolean checkPostEnabled(UUID post_id) {
        String sql = "SELECT enabled FROM posts WHERE post_id = ?";
        boolean enabled;

        try {
            Post target = jdbcTemplate.queryForObject(sql, new Object[]{post_id}, new PostMapper());
            enabled = target.isEnabled();
        } catch (DataAccessException exception) {
            exception.printStackTrace();
            enabled = false;
        }

        return enabled;
    }
}
