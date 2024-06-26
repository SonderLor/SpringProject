package ru.sonder.task15.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sonder.task15.models.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {}
