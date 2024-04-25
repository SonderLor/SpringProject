package ru.sonder.task18.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sonder.task18.models.User;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findUsersByFirstNameEquals(String firstName);
    List<User> findUserByLastNameEquals(String lastName);
    List<User> findUserByMiddleNameEquals(String middleName);
    List<User> findUserByBirthDateEquals(LocalDate birthDate);
}
