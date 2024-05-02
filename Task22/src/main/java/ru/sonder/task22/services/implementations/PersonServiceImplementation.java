package ru.sonder.task22.services.implementations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sonder.task22.DTOs.PersonDTO;
import ru.sonder.task22.models.Person;
import ru.sonder.task22.repositories.PersonRepository;
import ru.sonder.task22.services.EmailService;
import ru.sonder.task22.services.PersonService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PersonServiceImplementation implements PersonService {
    private final PersonRepository personRepository;
    private final EmailService emailService;

    public List<PersonDTO> getAllPersons() {
        log.info("getAllPersons method called");
        return personRepository.findAll().stream().map(Person::toDto).toList();
    }

    public PersonDTO getPersonById(Long id) {
        log.info("getPersonById method called with id: {}", id);
        return personRepository.findById(id).map(Person::toDto).orElse(null);
    }

    public PersonDTO saveOrUpdatePerson(PersonDTO userDTO) {
        log.info("saveOrUpdatePerson method called with userDTO: {}", userDTO);
        Person person = new Person();
        person.setFirstName(userDTO.getFirstName());
        person.setLastName(userDTO.getLastName());
        person.setMiddleName(userDTO.getMiddleName());
        person.setBirthDate(userDTO.getBirthDate());
        personRepository.save(person);
        String subject = "New User Added";
        String text = "Full Name: " + person.getLastName() + " " + person.getFirstName() + " " + person.getMiddleName() + "\nBirth Date: " + person.getBirthDate();
//        emailService.sendEmail(subject, text);
        return person.toDto();
    }

    public List<PersonDTO> saveOrUpdatePersons(List<PersonDTO> userDTOs) {
        log.info("saveOrUpdatePersons method called with userDTOs: {}", userDTOs);
        List<PersonDTO> savedUserDTOs = new ArrayList<>();
        for (PersonDTO userDTO : userDTOs) {
            PersonDTO user = saveOrUpdatePerson(userDTO);
            savedUserDTOs.add(user);
        }
        return savedUserDTOs;
    }

    public void deletePerson(Long userId) {
        log.info("deletePerson method called with userId: {}", userId);
        personRepository.deleteById(userId);
    }

    public void deletePersons() {
        log.info("deletePersons method called");
        personRepository.deleteAll();
    }

    public List<PersonDTO> getFilteredPersons(String filteredBy, String value) {
        log.info("getFilteredPersons method called with filteredBy: {} and value: {}", filteredBy, value);
        var entities = switch (filteredBy) {
            case "firstName" -> personRepository.findPersonByFirstNameEquals(value);
            case "lastName" -> personRepository.findPersonByLastNameEquals(value);
            case "middleName" -> personRepository.findPersonByMiddleNameEquals(value);
            case "birthDate" -> personRepository.findPersonByBirthDateEquals(LocalDate.parse(value));
            default -> throw new IllegalStateException("Unexpected value: " + filteredBy);
        };
        return entities.stream().map(Person::toDto).toList();
    }
}
