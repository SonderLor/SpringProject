package ru.sonder.task14.services;


import ru.sonder.task14.models.User;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    private final List<User> users = new ArrayList<>();
    private long ID = 0;

    {
        users.add(new User(++ID, "Ivan", "Ivanov", "Ivanovich", LocalDate.of(1990, 5, 15)));
        users.add(new User(++ID, "Peter", "Petrov", "Petrovich", LocalDate.of(1985, 8, 25)));
        users.add(new User(++ID, "Anna", "Sidorova", "Petrovna", LocalDate.of(2000, 3, 10)));
        users.add(new User(++ID, "Elena", "Smirnova", "Alexandrovna", LocalDate.of(1975, 11, 20)));
        users.add(new User(++ID, "Mikhail", "Kuznetsov", "Dmitrievich", LocalDate.of(1995, 9, 5)));
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User saveUser(User user) {
        user.setId(users.stream().mapToLong(User::getId).max().orElse(0) + 1);
        users.add(user);
        return user;
    }

    public void deleteUserById(Long id) {
        users.removeIf(user -> user.getId().equals(id));
    }

    public User getUserById(Long id) {
        return users.stream().filter(user -> Objects.equals(user.getId(), id)).findFirst().orElse(null);
    }
}
