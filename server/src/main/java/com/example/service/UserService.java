package com.example.service;

import com.example.model.User;
import com.example.repository.UserRepository;

public interface UserService  {
    User save(User user);
    User findById(long id);
    User findByLogin(String login);

    void deleteByLogin(String login);
    void deleteById(long id);
    void delete(User user);
}
