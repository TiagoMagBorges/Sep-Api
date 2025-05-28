package br.com.sep.sepapi.service;

import br.com.sep.sepapi.domain.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);

    void updateUser(User user);

    void deleteUser(Long userId);

    User getUserById(Long userId);

    User getUserByEmail(String email);

    List<User> getAllUsers();
}
