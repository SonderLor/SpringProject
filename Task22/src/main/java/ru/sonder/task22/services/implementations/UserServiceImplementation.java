package ru.sonder.task22.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.sonder.task22.DTOs.UserDTO;
import ru.sonder.task22.models.User;
import ru.sonder.task22.repositories.UserRepository;
import ru.sonder.task22.services.UserService;

import java.util.List;

@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository users;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = users.getByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), List.of(
                new SimpleGrantedAuthority("ROLE_USER")
        ));
    }

    @Override
    public void saveUser(UserDTO user) {
        User userEntity = new User();

        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));

        users.save(userEntity);
    }
}