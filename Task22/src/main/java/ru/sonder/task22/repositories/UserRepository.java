package ru.sonder.task22.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sonder.task22.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getByUsername(String username);
}
