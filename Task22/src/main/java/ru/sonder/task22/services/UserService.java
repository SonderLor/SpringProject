package ru.sonder.task22.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.sonder.task22.DTOs.UserDTO;

public interface UserService extends UserDetailsService {
    void saveUser(UserDTO user);
}
