package ru.sonder.task15.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sonder.task15.models.User;

public interface UserRepository extends JpaRepository<User, Long> {}
