package ru.sonder.task16.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sonder.task16.DTOs.UserDTO;
import ru.sonder.task16.models.User;
import ru.sonder.task16.repositories.UserRepository;

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

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}