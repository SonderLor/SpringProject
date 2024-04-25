package ru.sonder.task20.services.implementations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sonder.task20.DTOs.UserDTO;
import ru.sonder.task20.models.User;
import ru.sonder.task20.repositories.UserRepository;
import ru.sonder.task20.services.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;

    public List<UserDTO> getAllUsers() {
        log.info("getAllUsers method called");
        return userRepository.findAll().stream().map(User::toDto).toList();
    }

    public UserDTO getUserById(Long id) {
        log.info("getUserById method called with id: {}", id);
        return userRepository.findById(id).map(User::toDto).orElse(null);
    }

    public UserDTO saveOrUpdateUser(UserDTO userDTO) {
        log.info("saveOrUpdateUser method called with userDTO: {}", userDTO);
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setMiddleName(userDTO.getMiddleName());
        user.setBirthDate(userDTO.getBirthDate());
        userRepository.save(user);
        return user.toDto();
    }

    public List<UserDTO> saveOrUpdateUsers(List<UserDTO> userDTOs) {
        log.info("saveOrUpdateUsers method called with userDTOs: {}", userDTOs);
        List<UserDTO> savedUserDTOs = new ArrayList<>();
        for (UserDTO userDTO : userDTOs) {
            UserDTO user = saveOrUpdateUser(userDTO);
            savedUserDTOs.add(user);
        }
        return savedUserDTOs;
    }

    public void deleteUser(Long userId) {
        log.info("deleteUser method called with userId: {}", userId);
        userRepository.deleteById(userId);
    }

    public void deleteUsers() {
        log.info("deleteUsers method called");
        userRepository.deleteAll();
    }

    public List<UserDTO> getFilteredUsers(String filteredBy, String value) {
        log.info("getFilteredUsers method called with filteredBy: {} and value: {}", filteredBy, value);
        var entities = switch (filteredBy) {
            case "firstName" -> userRepository.findUsersByFirstNameEquals(value);
            case "lastName" -> userRepository.findUserByLastNameEquals(value);
            case "middleName" -> userRepository.findUserByMiddleNameEquals(value);
            case "birthDate" -> userRepository.findUserByBirthDateEquals(LocalDate.parse(value));
            default -> throw new IllegalStateException("Unexpected value: " + filteredBy);
        };
        return entities.stream().map(User::toDto).toList();
    }
}
