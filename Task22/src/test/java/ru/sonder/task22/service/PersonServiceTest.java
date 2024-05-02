package ru.sonder.task22.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.sonder.task22.DTOs.PersonDTO;
import ru.sonder.task22.models.Person;
import ru.sonder.task22.repositories.PersonRepository;
import ru.sonder.task22.services.EmailService;
import ru.sonder.task22.services.implementations.PersonServiceImplementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private PersonServiceImplementation personService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllPersons() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person(1L, "John", "Doe", "Smith", LocalDate.of(1990, 5, 15), new ArrayList<>()));
        persons.add(new Person(2L, "Jane", "Doe", "Smith", LocalDate.of(1992, 8, 25), new ArrayList<>()));

        when(personRepository.findAll()).thenReturn(persons);

        List<PersonDTO> result = personService.getAllPersons();

        assertEquals(2, result.size());
    }

    @Test
    void testGetPersonById() {
        Person person = new Person(1L, "John", "Doe", "Smith", LocalDate.of(1990, 5, 15), new ArrayList<>());

        when(personRepository.findById(1L)).thenReturn(Optional.of(person));

        PersonDTO result = personService.getPersonById(1L);

        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
    }

    @Test
    void testSaveOrUpdatePerson() {
        PersonDTO personDTO = new PersonDTO(1L, "John", "Doe", "Smith", LocalDate.of(1990, 5, 15), List.of());
        PersonDTO result = personService.saveOrUpdatePerson(personDTO);

        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
    }

    @Test
    void testSaveOrUpdatePersons() {
        List<PersonDTO> personDTOs = new ArrayList<>();
        personDTOs.add(new PersonDTO(null, "John", "Doe", "Smith", LocalDate.of(1990, 5, 15), List.of()));
        personDTOs.add(new PersonDTO(null, "Jane", "Doe", "Smith", LocalDate.of(1992, 8, 25), List.of()));

        when(personRepository.save(any(Person.class))).thenReturn(new Person());

        List<PersonDTO> result = personService.saveOrUpdatePersons(personDTOs);

        assertEquals(2, result.size());
    }

    @Test
    void testDeletePerson() {
        Long userId = 1L;

        personService.deletePerson(userId);

        verify(personRepository, times(1)).deleteById(userId);
    }

    @Test
    void testDeletePersons() {
        personService.deletePersons();

        verify(personRepository, times(1)).deleteAll();
    }

    @Test
    void testGetFilteredPersons() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person(1L, "John", "Doe", "Smith", LocalDate.of(1990, 5, 15), new ArrayList<>()));
        persons.add(new Person(2L, "Jane", "Doe", "Smith", LocalDate.of(1992, 8, 25), new ArrayList<>()));

        when(personRepository.findPersonByLastNameEquals("Doe")).thenReturn(persons);

        List<PersonDTO> result = personService.getFilteredPersons("lastName", "Doe");

        assertEquals(2, result.size());
    }

}
