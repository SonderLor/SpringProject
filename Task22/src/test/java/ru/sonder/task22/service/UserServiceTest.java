package ru.sonder.task22.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.sonder.task22.DTOs.UserDTO;
import ru.sonder.task22.models.User;
import ru.sonder.task22.repositories.UserRepository;
import ru.sonder.task22.services.implementations.UserServiceImplementation;

public class UserServiceTest {
    @Test
    @DisplayName("Тестирование UserService#loadUserByUsername")
    public void loadUserByUsernameShouldReturnUser() {
        var passwordEncoder = Mockito.mock(PasswordEncoder.class);
        var userRepository = Mockito.mock(UserRepository.class);

        var userService = new UserServiceImplementation(
                passwordEncoder, userRepository
        );

        Mockito.when(userRepository.getByUsername("user")).thenReturn(new User("user", "password"));

        var user = userService.loadUserByUsername("user");
        Assertions.assertThat(user.getUsername()).isEqualTo("user");
    }

    @Test
    @DisplayName("Тестирование UserService#saveUser")
    public void saveUserShouldSaveUser() {
        var passwordEncoder = Mockito.mock(PasswordEncoder.class);
        var userRepository = Mockito.mock(UserRepository.class);

        var userService = new UserServiceImplementation(
                passwordEncoder,
                userRepository
        );

        userService.saveUser(new UserDTO("user", "password"));

        Mockito.verify(passwordEncoder).encode("password");
        Mockito.verify(userRepository).save(Mockito.any(User.class));
    }
}
