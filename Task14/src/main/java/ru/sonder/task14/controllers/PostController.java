package ru.sonder.task14.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.sonder.task14.models.Post;
import ru.sonder.task14.services.PostService;

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
    public Post createUser(@RequestBody Post user) {
        return postService.savePost(user);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePostById(id);
    }
}
