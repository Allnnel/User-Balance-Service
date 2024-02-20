package com.example.controller;

import com.example.exception.DuplicateUserException;
import com.example.exception.UserNotFoundException;
import com.example.model.User;
import com.example.response.ResponseMessage;
import com.example.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  /**
   * Метод контроллера для обработки GET-запроса по адресу "/users". Получает список пользователей
   * из сервиса и передает его в представление.
   *
   * @param model объект Model, предоставляемый Spring MVC для передачи данных в представление.
   * @return Ответ в формате JSON, содержащий статус операции, код состояния и объект баланса.
   */
  @GetMapping("/users")
  public ResponseEntity<String> getUsersPage(Model model) {
    List<User> users = userService.getAllUsers();
    if (users.isEmpty()) {
      ResponseMessage response = new ResponseMessage("Failed", "500", null);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response.toJSON());
    }
    model.addAttribute("balances", users);
    ResponseMessage response = new ResponseMessage("Success", "200", model);
    return ResponseEntity.status(HttpStatus.CREATED).body(response.toJSON());
  }

  /**
   * Метод контроллера для обработки POST-запроса по адресу "/users". Этот метод принимает данные
   * формы, отправленные пользователем, и выполняет необходимые действия. Сохраняет нового
   * пользователя в базе данных.
   *
   * @param user объект пользователя, переданный из формы на веб-странице.
   * @return Ответ в формате JSON, содержащий статус операции, код состояния и объект баланса.
   */
  @PostMapping("/users")
  public ResponseEntity<String> postUsersPage(@RequestBody User user) {
    try {
      userService.save(user);
    } catch (DuplicateUserException e) {
      ResponseMessage response = new ResponseMessage("Failed", "500", null);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response.toJSON());
    }
    ResponseMessage response = new ResponseMessage("Success", "200", user);
    return ResponseEntity.status(HttpStatus.CREATED).body(response.toJSON());
  }

  /**
   * Метод контроллера для обновления данных пользователя по адресу "/users".
   *
   * @param user объект пользователя, содержащий обновленные данные.
   * @return Ответ в формате JSON, содержащий статус операции, код состояния и объект баланса.
   */
  @PutMapping("/users")
  public ResponseEntity<String> putUsersPage(@RequestBody User user) {
    userService.update(user);
    ResponseMessage response = new ResponseMessage("Success", "200", user);
    return ResponseEntity.status(HttpStatus.CREATED).body(response.toJSON());
  }

  /**
   * Метод контроллера для удаления пользователя по логину. Принимает логин пользователя в качестве
   * параметра из пути URL и передает его сервису пользователя для выполнения операции удаления.
   * После успешного удаления пользователя перенаправляет пользователя на страницу пользователей.
   *
   * @param login Логин пользователя, которого нужно удалить.
   * @return Ответ в формате JSON, содержащий статус операции, код состояния и объект баланса.
   */
  @DeleteMapping("/users/{login}")
  public ResponseEntity<String> deleteUsersPage(@PathVariable String login) {
    try {
      userService.deleteByLogin(login);
    } catch (UserNotFoundException e) {
      ResponseMessage response = new ResponseMessage("Failed", "500", null);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response.toJSON());
    }
    ResponseMessage response = new ResponseMessage("Success", "200", login);
    return ResponseEntity.status(HttpStatus.CREATED).body(response.toJSON());
  }
}
