package ru.sonder.task14.services;


import ru.sonder.task14.models.Post;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PostService {
    private final List<Post> posts = new ArrayList<>();
    private long ID = 0;

    {
        posts.add(new Post(++ID, "Text 1", LocalDate.now()));
        posts.add(new Post(++ID, "Text 2", LocalDate.now()));
        posts.add(new Post(++ID, "Text 3", LocalDate.now()));
        posts.add(new Post(++ID, "Text 4", LocalDate.now()));
        posts.add(new Post(++ID, "Text 5", LocalDate.now()));
    }

    public List<Post> getAllPosts() {
        return posts;
    }

    public Post savePost(Post post) {
        post.setId(posts.stream().mapToLong(Post::getId).max().orElse(0) + 1);
        posts.add(post);
        return post;
    }

    public void deletePostById(Long id) {
        posts.removeIf(post -> post.getId().equals(id));
    }

    public Post getPostById(Long id) {
        return posts.stream().filter(post -> Objects.equals(post.getId(), id)).findFirst().orElse(null);
    }
}
