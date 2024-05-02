package ru.sonder.task22.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sonder.task22.models.Person;
import ru.sonder.task22.models.Post;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findPostByTextContains(String text);
    List<Post> findPostByCreationDateEquals(LocalDate creationDate);
    List<Post> findPostByPersonEquals(Person person);
}
