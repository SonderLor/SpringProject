package ru.sonder.task22.services;

import ru.sonder.task22.DTOs.PersonDTO;

import java.util.List;

public interface PersonService {
    List<PersonDTO> getAllPersons();
    PersonDTO getPersonById(Long id);
    PersonDTO saveOrUpdatePerson(PersonDTO userDTO);
    List<PersonDTO> saveOrUpdatePersons(List<PersonDTO> userDTOs);
    void deletePerson(Long userId);
    void deletePersons();
    List<PersonDTO> getFilteredPersons(String filteredBy, String value);
}
