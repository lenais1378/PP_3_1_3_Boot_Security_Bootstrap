package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;


public interface UserService {
    User findUserById(Long userId);

    User findByUsername(String email);

    List<User> allUsers();

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(Long userId);
}
