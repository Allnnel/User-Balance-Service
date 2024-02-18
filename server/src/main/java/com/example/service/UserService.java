package com.example.service;

import com.example.model.User;
import com.example.repository.UserRepository;

import java.util.List;

public interface UserService  {
    void save(User user);
    User findById(long id);
    User findByLogin(String login);

    void deleteByLogin(String login);
    void deleteById(long id);
    void delete(User user);
    List<User> getAllUsers();
    void update(User user);
}
