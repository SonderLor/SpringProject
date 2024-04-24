package ru.sonder.task16.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sonder.task16.models.User;

public interface UserRepository extends JpaRepository<User, Long> {}
