package com.example.service;

import com.example.exception.CustomException;
import com.example.model.User;
import java.util.List;

public interface UserService {
  void save(User user) throws CustomException;

  User findById(long id) throws CustomException;

  User findByLogin(String login) throws CustomException;

  void deleteByLogin(String login) throws CustomException;

  void deleteById(long id) throws CustomException;

  void delete(User user) throws CustomException;

  List<User> getAllUsers();

  void update(User user);
}
