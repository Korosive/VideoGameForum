package com.app.VideoGameForum.controller;

import com.app.VideoGameForum.model.Post;
import com.app.VideoGameForum.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping(value = "/post/create", consumes = "application/json", produces = "application/json")
    public HashMap<String, Object> createPost(@RequestBody Post new_post) {
        return postService.createPost(new_post);
    }

    @GetMapping(value = "/get/thread/{post_id}", produces = "application/json")
    public HashMap<String, Object> getPostThread(@PathVariable UUID post_id) {
        return postService.getPostThread(post_id);
    }

    @GetMapping(value = "/get/live", produces = "application/json")
    public HashMap<String, Object> getLivePosts() {
        return postService.getLivePosts();
    }

    @GetMapping(value = "/get/all", produces = "application/json")
    public HashMap<String, Object> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping(value = "/post/user/{username}", produces = "application/json")
    public HashMap<String, Object> getUserPosts(@PathVariable String username) {
        return postService.getUserPosts(username);
    }

    @PutMapping(value = "/post/disable/{post_id}", produces = "application/json")
    public HashMap<String, Object> disablePost(@PathVariable UUID post_id) {
        return postService.disablePost(post_id);
    }

    @PutMapping(value = "/post/enable/{post_id}", produces = "application/json")
    public HashMap<String, Object> enablePost(@PathVariable UUID post_id) {
        return postService.enablePost(post_id);
    }

    @PutMapping(value = "/post/update", consumes = "application/json", produces = "application/json")
    public HashMap<String, Object> updatePost(@RequestBody Post update_post) {
        return postService.updatePost(update_post);
    }
}
