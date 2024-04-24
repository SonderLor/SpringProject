package ru.sonder.task16.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sonder.task16.DTOs.PostDTO;
import ru.sonder.task16.models.Post;
import ru.sonder.task16.models.User;
import ru.sonder.task16.repositories.PostRepository;
import ru.sonder.task16.repositories.UserRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public List<PostDTO> getAllPosts() {
        return postRepository.findAll().stream().map(Post::toDto).toList();
    }

    public PostDTO getPostById(Long id) {
        return postRepository.findById(id).map(Post::toDto).orElse(null);
    }

    public PostDTO saveOrUpdatePost(PostDTO postDTO) {
        Post post = new Post();
        post.setText(postDTO.getText());
        post.setCreationDate(postDTO.getCreationDate());
        User user = userRepository.findById(postDTO.getUserId()).orElseThrow();
        post.setUser(user);
        user.getPosts().add(post);
        postRepository.save(post);
        return post.toDto();
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }
}
