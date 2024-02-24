package com.example.service;

import com.example.exception.CustomException;
import com.example.model.Balance;
import com.example.model.User;
import com.example.repository.BalanceRepository;
import com.example.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final BalanceRepository balanceRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository, BalanceRepository balanceRepository) {
    this.userRepository = userRepository;
    this.balanceRepository = balanceRepository;
  }

  @Override
  public void save(User user) throws CustomException {
    if (userRepository.findByLogin(user.getLogin()).isPresent()) {
      throw new CustomException("User already exists.", 3);
    }
    userRepository.save(user);
  }

  @Override
  public User findByLogin(String login) throws CustomException {
    Optional<User> user = userRepository.findByLogin(login);
    if (!user.isPresent()) {
      throw new CustomException("User not found.", 4);
    }
    return user.get();
  }

  @Override
  public User findById(long id) throws CustomException {
    Optional<User> user = userRepository.findById(id);
    if (!user.isPresent()) {
      throw new CustomException("User not found.", 4);
    }
    return user.get();
  }

  @Override
  public void deleteByLogin(String login) throws CustomException {
    User deleteUser = findByLogin(login);
    userRepository.delete(deleteUser);
  }

  @Override
  public void deleteById(long id) throws CustomException {
    User deleteUser = findById(id);
    userRepository.delete(deleteUser);
  }

  @Override
  public void delete(User user) throws CustomException {
    User deleteUser = findByLogin(user.getLogin());
    userRepository.delete(deleteUser);
    Optional<Balance> balance = balanceRepository.findByUserLogin(user.getLogin());
    balance.ifPresent(balanceRepository::delete);
  }

  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @Override
  public void update(User user) {
    Optional<User> optionalUser = userRepository.findByLogin(user.getLogin());
    if (optionalUser.isPresent()) {
      User existingUser = optionalUser.get();
      if (user.getBirthDay() != null) existingUser.setBirthDay(user.getBirthDay());
      if (user.getEmail() != null) existingUser.setEmail(user.getEmail());
      if (user.getLogin() != null) existingUser.setLogin(user.getLogin());
      if (user.getMobilePhone() != null) existingUser.setMobilePhone(user.getMobilePhone());
      if (user.getPasswordHash() != null) existingUser.setPasswordHash(user.getPasswordHash());
      userRepository.save(existingUser);
    } else {
      userRepository.save(user);
    }
  }
}
