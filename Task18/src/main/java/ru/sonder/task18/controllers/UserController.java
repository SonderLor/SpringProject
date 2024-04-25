package ru.sonder.task18.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.sonder.task18.DTOs.UserDTO;
import ru.sonder.task18.services.UserService;

import java.util.List;

@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDTO userInfo(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return userService.saveOrUpdateUser(userDTO);
    }

    @PostMapping("/list")
    public List<UserDTO> createUsers(@RequestBody List<UserDTO> usersDTO) {
        return userService.saveOrUpdateUsers(usersDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @DeleteMapping
    public void deleteUsers() {
        userService.deleteUsers();
    }

    @GetMapping("/filtered")
    public List<UserDTO> getUniversities(@RequestParam String filteredBy, @RequestParam String value) {
        return userService.getFilteredUsers(filteredBy, value);
    }
}
