package com.example.controller;

import com.example.exception.CustomException;
import com.example.model.User;
import com.example.response.ResponseMessage;
import com.example.response.UserResponseMessage;
import com.example.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/users")
  public ResponseEntity<ResponseMessage> getUsersPage() {
    List<User> users = userService.getAllUsers();
    if (users.isEmpty()) {
      ResponseMessage response =
          new UserResponseMessage("Failed", "The table is empty.", "500", null);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
    ResponseMessage response = new UserResponseMessage("Success", null, "200", users);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @PostMapping("/users")
  public ResponseEntity<ResponseMessage> postUsersPage(@RequestBody User user) {
    try {
      userService.save(user);
    } catch (CustomException e) {
      ResponseMessage response = new UserResponseMessage("Failed", e.getMessage(), "500", null);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
    ResponseMessage response = new UserResponseMessage("Success", null, "200", user);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PutMapping("/users")
  public ResponseEntity<ResponseMessage> putUsersPage(@RequestBody User user) {
    userService.update(user);
    ResponseMessage response = new UserResponseMessage("Success", null, "200", user);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @DeleteMapping("/users")
  public ResponseEntity<ResponseMessage> deleteUsersPage(@RequestBody User user) {
    try {
      userService.delete(user);
    } catch (CustomException e) {
      ResponseMessage response = new UserResponseMessage("Failed", e.getMessage(), "500", null);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
    ResponseMessage response = new UserResponseMessage("Success", null, "200", user);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }
}
