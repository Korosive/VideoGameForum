package com.app.VideoGameForum.controller;

import com.app.VideoGameForum.model.User;
import com.app.VideoGameForum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/user/create", consumes = "application/json", produces = "application/json")
    public HashMap<String, Object> createAccount(@RequestBody User new_user) {
        return userService.createAccount(new_user);
    }

    @GetMapping(value = "/user/login", produces = "application/json")
    public HashMap<String, Object> processLogin(@RequestBody User login_user) {
        return userService.processLogin(login_user);
    }

    @PutMapping(value = "/user/update/email", consumes = "application/json", produces = "application/json")
    public HashMap<String, Object> updateEmail(@RequestBody HashMap<String, Object> new_email) {
        return userService.updateUserEmail(new_email);
    }

    @PutMapping(value = "/user/update/username", consumes = "application/json", produces = "application/json")
    public HashMap<String, Object> updateUsername(@RequestBody HashMap<String, Object> new_username) {
        return userService.updateUsername(new_username);
    }

    @PutMapping(value = "/user/update/password", consumes = "application/json", produces = "application/json")
    public HashMap<String, Object> updatePassword(@RequestBody HashMap<String, Object> new_password) {
        return userService.updatePassword(new_password);
    }
}
