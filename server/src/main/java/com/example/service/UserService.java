package com.example.service;

import com.example.exception.DuplicateUserException;
import com.example.exception.UserNotFoundException;
import com.example.model.User;
import java.util.List;

public interface UserService {
  void save(User user) throws DuplicateUserException;

  User findById(long id) throws UserNotFoundException;

  User findByLogin(String login) throws UserNotFoundException;

  void deleteByLogin(String login) throws UserNotFoundException;

  void deleteById(long id) throws UserNotFoundException;

  void delete(User user) throws UserNotFoundException;

  List<User> getAllUsers();

  void update(User user);
}
