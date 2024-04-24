package ru.sonder.task17.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sonder.task17.models.Post;
import ru.sonder.task17.models.User;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findPostByTextEquals(String text);
    List<Post> findUserByCreationDateEquals(LocalDate creationDate);
    List<Post> findUserByUserEquals(User user);
}
