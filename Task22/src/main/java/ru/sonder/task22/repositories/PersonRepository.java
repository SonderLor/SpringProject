package ru.sonder.task22.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sonder.task22.models.Person;

import java.time.LocalDate;
import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findPersonByFirstNameEquals(String firstName);
    List<Person> findPersonByLastNameEquals(String lastName);
    List<Person> findPersonByMiddleNameEquals(String middleName);
    List<Person> findPersonByBirthDateEquals(LocalDate birthDate);
}
