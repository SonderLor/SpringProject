package ru.sonder.task17.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sonder.task17.DTOs.UserDTO;
import ru.sonder.task17.models.User;
import ru.sonder.task17.repositories.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(User::toDto).toList();
    }

    public UserDTO getUserById(Long id) {
        return userRepository.findById(id).map(User::toDto).orElse(null);
    }

    public UserDTO saveOrUpdateUser(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setMiddleName(userDTO.getMiddleName());
        user.setBirthDate(userDTO.getBirthDate());
        userRepository.save(user);
        return user.toDto();
    }

    public List<UserDTO> saveOrUpdateUsers(List<UserDTO> userDTOs) {
        List<UserDTO> savedUserDTOs = new ArrayList<>();
        for (UserDTO userDTO : userDTOs) {
            UserDTO user = saveOrUpdateUser(userDTO);
            savedUserDTOs.add(user);
        }
        return savedUserDTOs;
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public void deleteUsers() {
        userRepository.deleteAll();
    }

    public List<UserDTO> getFilteredUsers(String filteredBy, String value) {
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