package ru.sonder.task23.services;

import ru.sonder.task22.DTOs.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long id);
    UserDTO saveOrUpdateUser(UserDTO userDTO);
    List<UserDTO> saveOrUpdateUsers(List<UserDTO> userDTOs);
    void deleteUser(Long userId);
    void deleteUsers();
    List<UserDTO> getFilteredUsers(String filteredBy, String value);
}
