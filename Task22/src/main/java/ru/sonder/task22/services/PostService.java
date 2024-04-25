package ru.sonder.task22.services;

import ru.sonder.task22.DTOs.PostDTO;

import java.util.List;

public interface PostService {
    List<PostDTO> getAllPosts();
    PostDTO getPostById(Long id);
    PostDTO saveOrUpdatePost(PostDTO postDTO);
    void deletePost(Long postId);
    List<PostDTO> getFilteredPosts(String filteredBy, String value);
}
