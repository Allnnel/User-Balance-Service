package com.example.service;

import com.example.exception.DuplicateUserException;
import com.example.exception.UserNotFoundException;
import com.example.model.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("userService")
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(@Qualifier("userRepository") UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        if (userRepository.findByLogin(user.getLogin()) != null) {
            throw new DuplicateUserException();
        }
       User savedUser = userRepository.save(user);
       if (savedUser == null) {
           throw new UserNotFoundException();
       }
       return savedUser;
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

}
