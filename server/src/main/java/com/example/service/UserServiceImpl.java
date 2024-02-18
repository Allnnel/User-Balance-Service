package com.example.service;

import com.example.exception.DuplicateUserException;
import com.example.exception.UserNotFoundException;
import com.example.model.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component("userService")
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(@Qualifier("userRepository") UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(User user) {
        if (userRepository.findByLogin(user.getLogin()) != null) {
            throw new DuplicateUserException();
        }
       userRepository.save(user);
    }

    @Override
    public User findByLogin(String login) {
        User user = userRepository.findByLogin(login);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }

    @Override
    public User findById(long id) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }

    @Override
    public void deleteByLogin(String login) {
        findByLogin(login);
        userRepository.deleteByLogin(login);
    }

    @Override
    public void deleteById(long id) {
        findById(id);
        userRepository.deleteById(id);
    }

    @Override
    public void delete(User user) {
        findById(user.getId());
        userRepository.deleteById(user.getId());
    }
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Override
    public void update(User user) {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findById(user.getId()));
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            if (user.getBirthDay() != null)
                existingUser.setBirthDay(user.getBirthDay());
            if (user.getEmail() != null)
                existingUser.setEmail(user.getEmail());
            if (user.getLogin() != null)
                existingUser.setLogin(user.getLogin());
            if (user.getMobilePhone() != null)
                existingUser.setMobilePhone(user.getMobilePhone());
            if (user.getPasswordHash() != null)
                existingUser.setPasswordHash(user.getPasswordHash());
            userRepository.save(existingUser);
        } else {
            userRepository.save(user);
        }
    }


}
