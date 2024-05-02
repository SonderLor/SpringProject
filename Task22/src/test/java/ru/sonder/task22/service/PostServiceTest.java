package ru.sonder.task22.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.sonder.task22.DTOs.PostDTO;
import ru.sonder.task22.models.Person;
import ru.sonder.task22.models.Post;
import ru.sonder.task22.repositories.PersonRepository;
import ru.sonder.task22.repositories.PostRepository;
import ru.sonder.task22.services.EmailService;
import ru.sonder.task22.services.implementations.PersonServiceImplementation;
import ru.sonder.task22.services.implementations.PostServiceImplementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private PersonServiceImplementation personService;

    @InjectMocks
    private PostServiceImplementation postService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllPosts() {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post(1L, "Test post 1", LocalDate.now(), null));
        posts.add(new Post(2L, "Test post 2", LocalDate.now(), null));
        when(postRepository.findAll()).thenReturn(posts);
        List<PostDTO> result = postService.getAllPosts();
        assertEquals(2, result.size());
    }

    @Test
    void testGetPostById() {
        Post post = new Post(1L, "Test post", LocalDate.now(), null);
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        PostDTO result = postService.getPostById(1L);
        assertEquals("Test post", result.getText());
    }

    @Test
    void testSaveOrUpdatePost() {
        Person person = new Person(1L, "John", "Doe", "Smith", LocalDate.of(1990, 5, 15), new ArrayList<>());
        Post post = new Post(1L, "Test post", LocalDate.now(), person);
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        when(postRepository.save(any(Post.class))).thenReturn(post);
        personService.saveOrUpdatePerson(person.toDto());
        PostDTO result = postService.saveOrUpdatePost(post.toDto());
        assertEquals("Test post", result.getText());
    }

    @Test
    void testDeletePost() {
        Long postId = 1L;
        postService.deletePost(postId);
        verify(postRepository, times(1)).deleteById(postId);
    }

    @Test
    void testGetFilteredPosts() {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post(1L, "Test post", LocalDate.now(), null));
        posts.add(new Post(2L, "Test post", LocalDate.now(), null));
        when(postRepository.findPostByTextContains("Test post")).thenReturn(posts);
        List<PostDTO> result = postService.getFilteredPosts("text", "Test post");
        assertEquals(2, result.size());
    }

}
