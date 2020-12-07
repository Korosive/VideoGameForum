package com.app.VideoGameForum.controller;

import com.app.VideoGameForum.model.Comment;
import com.app.VideoGameForum.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping(value = "/user/{username}", produces = "application/json")
    public HashMap<String, Object> getUserComments(@PathVariable String username) {
        return commentService.getUserComments(username);
    }

    @PutMapping(value = "/comment/update", consumes = "application/json", produces = "application/json")
    public HashMap<String, Object> updateComment(@RequestBody Comment update_comment) {
        return commentService.updateComment(update_comment);
    }

    @PostMapping(value = "/comment/create", consumes = "application/json", produces = "application/json")
    public HashMap<String, Object> createComment(@RequestBody Comment new_comment) {
        return commentService.createComment(new_comment);
    }

    @PutMapping(value = "/comment/disable/{comment_id}", produces = "application/json")
    public HashMap<String, Object> disableComment(@PathVariable UUID comment_id) {
        return commentService.disableComment(comment_id);
    }

    @PutMapping(value = "/comment/enable/{comment_id}", produces = "application/json")
    public HashMap<String, Object> enableComment(@PathVariable UUID comment_id) {
        return commentService.enableComment(comment_id);
    }
}
