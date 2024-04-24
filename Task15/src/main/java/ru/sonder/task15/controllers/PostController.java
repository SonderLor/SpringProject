package ru.sonder.task15.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.sonder.task15.models.Post;
import ru.sonder.task15.services.PostService;

import java.util.List;

@RequestMapping("/posts")
@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public Post postInfo(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @PostMapping
    public void createUser(@RequestBody Post user) {
        postService.saveOrUpdatePost(user);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }
}
