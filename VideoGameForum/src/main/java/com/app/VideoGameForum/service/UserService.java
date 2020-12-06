package com.app.VideoGameForum.service;

import com.app.VideoGameForum.model.User;
import com.app.VideoGameForum.util.UserMapper;
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
public class UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    public HashMap<String, Object> createAccount(User new_user) {
        String sql = "INSERT INTO comments (user_id, email, username, password, date_created, last_updated, enabled) VALUES (?, ?, ?, ?, ?, ?, ?)";
        HashMap<String, Object> response = new HashMap<>();
        UUID user_id = UUID.randomUUID();
        String email = new_user.getEmail();
        String username = new_user.getUsername();
        String plain_password = new_user.getPassword();
        Date date_created = new Date(), last_updated = new Date();

        if (checkEmail(email)) {
            if (checkUsername(username)) {
                try {
                    String encrypted_password = org.mindrot.jbcrypt.BCrypt.hashpw(plain_password, org.mindrot.jbcrypt.BCrypt.gensalt());
                    jdbcTemplate.update(sql, user_id, email, username, encrypted_password, date_created, last_updated, true);
                } catch (DataAccessException exception) {
                    exception.printStackTrace();
                    response.put("success", false);
                    response.put("message", "Error in creating account.");
                    log.info("Failed to create account at {}.", new Date());
                }
            } else {
                response.put("success", false);
                response.put("message", "Username already exists. Please try another one.");
                log.info("Username already exists at {}.", new Date());
            }
        } else {
            response.put("success", false);
            response.put("message", "Email already exists. Please try another one.");
            log.info("Email already exists at {}.", new Date());
        }

        return response;
    }

    private boolean checkEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?;";
        boolean exists;

        try {
            List<User> list = jdbcTemplate.queryForList(sql, new Object[]{email}, User.class);
            exists = list.size() != 0;
        } catch (DataAccessException exception) {
            exception.printStackTrace();
            exists = false;
        }

        return exists;
    }

    private boolean checkUsername(String username) {
        String sql = "SELECT username FROM users WHERE username = ?;";
        boolean exists;

        try {
            List<User> list = jdbcTemplate.queryForList(sql, new Object[]{username}, User.class);
            exists = list.size() != 0;
        } catch (DataAccessException exception) {
            exception.printStackTrace();
            exists = false;
        }

        return exists;
    }

    public HashMap<String, Object> processLogin(User login_user) {
        String sql = "SELECT * FROM users WHERE user_id = ?;";
        HashMap<String, Object> response = new HashMap<>();
        String username = login_user.getUsername();
        String password = login_user.getPassword();
        UUID user_id = login_user.getUser_id();

        try {
            User db_user = jdbcTemplate.queryForObject(sql, new Object[]{user_id}, new UserMapper());
            if (username.equals(db_user.getUsername())) {
                if (org.mindrot.jbcrypt.BCrypt.checkpw(password, db_user.getPassword())) {
                    response.put("success", true);
                    response.put("message", "Successfully logged in.");
                    log.info("User {} logged in at {}.", user_id, new Date());
                } else {
                    response.put("success", false);
                    response.put("message", "Password does not match.");
                    log.info("Password does not match for user {} at {}.", user_id, new Date());
                }
            } else {
                response.put("success", false);
                response.put("message", "Username does not exist.");
                log.info("Username does not exist in database at {}.", new Date());
            }
        } catch (DataAccessException exception) {
            exception.printStackTrace();
            response.put("success", false);
            response.put("message", "Error in attempting to login.");
            log.info("Error in attempting to login at {}.", new Date());
        }

        return response;
    }
}
