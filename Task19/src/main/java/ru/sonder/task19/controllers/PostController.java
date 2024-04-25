package ru.sonder.task19.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.sonder.task19.DTOs.PostDTO;
import ru.sonder.task19.services.PostService;

import java.util.List;

@RequestMapping("/posts")
@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping
    public List<PostDTO> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public PostDTO getPost(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @PostMapping
    public PostDTO createPost(@RequestBody PostDTO postDTO) {
        return postService.saveOrUpdatePost(postDTO);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }

    @GetMapping("/filtered")
    public List<PostDTO> getUniversities(@RequestParam String filteredBy, @RequestParam String value) {
        return postService.getFilteredPosts(filteredBy, value);
    }
}
