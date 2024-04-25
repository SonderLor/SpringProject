package ru.sonder.task22.services.implementations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sonder.task22.DTOs.PostDTO;
import ru.sonder.task22.models.Post;
import ru.sonder.task22.models.User;
import ru.sonder.task22.repositories.PostRepository;
import ru.sonder.task22.repositories.UserRepository;
import ru.sonder.task22.services.EmailService;
import ru.sonder.task22.services.PostService;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PostServiceImplementation implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    public List<PostDTO> getAllPosts() {
        log.info("getAllPosts method called");
        return postRepository.findAll().stream().map(Post::toDto).toList();
    }

    public PostDTO getPostById(Long id) {
        log.info("getPostById method called with id: {}", id);
        return postRepository.findById(id).map(Post::toDto).orElse(null);
    }

    public PostDTO saveOrUpdatePost(PostDTO postDTO) {
        log.info("saveOrUpdatePost method called with postDTO: {}", postDTO);
        Post post = new Post();
        post.setText(postDTO.getText());
        post.setCreationDate(postDTO.getCreationDate());
        User user = userRepository.findById(postDTO.getUserId()).orElseThrow();
        post.setUser(user);
        user.getPosts().add(post);
        postRepository.save(post);
        String subject = "New Post Added";
        String text = "Text: " + post.getText() + "\nCreation Date: " + post.getCreationDate();
//        emailService.sendEmail(subject, text);
        return post.toDto();
    }

    public void deletePost(Long postId) {
        log.info("deletePost method called with postId: {}", postId);
        postRepository.deleteById(postId);
    }

    public List<PostDTO> getFilteredPosts(String filteredBy, String value) {
        log.info("getFilteredPosts method called with filteredBy: {} and value: {}", filteredBy, value);
        var entities = switch (filteredBy) {
            case "text" -> postRepository.findPostByTextEquals(value);
            case "creationDate" -> postRepository.findUserByCreationDateEquals(LocalDate.parse(value));
            case "userId" -> postRepository.findUserByUserEquals(userRepository.findById(Long.valueOf(value)).orElseThrow());
            default -> throw new IllegalStateException("Unexpected value: " + filteredBy);
        };
        return entities.stream().map(Post::toDto).toList();
    }
}
