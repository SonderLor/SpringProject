package ru.sonder.task22.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.sonder.task22.DTOs.PersonDTO;
import ru.sonder.task22.services.PersonService;

import java.util.List;

@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @GetMapping
    public List<PersonDTO> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("/{id}")
    public PersonDTO personInfo(@PathVariable Long id) {
        return personService.getPersonById(id);
    }

    @PostMapping
    public PersonDTO createPersons(@RequestBody PersonDTO userDTO) {
        return personService.saveOrUpdatePerson(userDTO);
    }

    @PostMapping("/list")
    public List<PersonDTO> createPersons(@RequestBody List<PersonDTO> usersDTO) {
        return personService.saveOrUpdatePersons(usersDTO);
    }

    @DeleteMapping("/{id}")
    public void deletePersons(@PathVariable Long id) {
        personService.deletePerson(id);
    }

    @DeleteMapping
    public void deletePersons() {
        personService.deletePersons();
    }

    @GetMapping("/filtered")
    public List<PersonDTO> getPersons(@RequestParam String filteredBy, @RequestParam String value) {
        return personService.getFilteredPersons(filteredBy, value);
    }
}
