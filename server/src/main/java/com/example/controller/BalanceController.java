package com.example.controller;

import com.example.exception.BalanceNotFoundException;
import com.example.exception.DuplicateBalanceException;
import com.example.model.Balance;
import com.example.response.ResponseMessage;
import com.example.service.BalanceService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BalanceController {

  private final BalanceService balanceService;

  @Autowired
  public BalanceController(BalanceService balanceService) {
    this.balanceService = balanceService;
  }

  /**
   * Метод для отображения страницы с балансами пользователей. Получает список всех балансов с
   * помощью сервиса и добавляет его в модель, затем возвращает имя представления для отображения
   * страницы балансов.
   *
   * @param model Модель для передачи данных представлению.
   * @return Ответ в формате JSON, содержащий статус операции, код состояния и объект баланса.
   */
  @GetMapping("/balances")
  public ResponseEntity<String> getUsersPage(Model model) {
    List<Balance> balances = balanceService.getAllBalances();
    if (balances.isEmpty()) {
      ResponseMessage response = new ResponseMessage("Failed", "500", null);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response.toJSON());
    }
    model.addAttribute("balances", balances);
    ResponseMessage response = new ResponseMessage("Success", "200", model);
    return ResponseEntity.status(HttpStatus.CREATED).body(response.toJSON());
  }

  /**
   * Метод для обработки запроса на добавление нового баланса пользователя. Принимает объект баланса
   * в качестве параметра, сохраняет его с помощью сервиса и возвращает ответ в формате JSON.
   *
   * @param balance Объект баланса для сохранения.
   * @return Ответ в формате JSON, содержащий статус операции, код состояния и объект баланса.
   */
  @PostMapping("/balances")
  public ResponseEntity<String> postUsersPage(@RequestBody Balance balance) {
    try {
      balanceService.save(balance);
    } catch (DuplicateBalanceException e) {
      ResponseMessage response = new ResponseMessage("Failed", "500", null);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response.toJSON());
    }
    ResponseMessage response = new ResponseMessage("Success", "200", balance);
    return ResponseEntity.status(HttpStatus.CREATED).body(response.toJSON());
  }

  /**
   * Метод для обработки запроса на обновление баланса пользователя. Принимает объект баланса в
   * качестве параметра, обновляет его с помощью сервиса и перенаправляет на страницу с балансами.
   *
   * @param balance Объект баланса для обновления.
   * @return Ответ в формате JSON, содержащий статус операции, код состояния и объект баланса.
   */
  @PutMapping("/balances")
  public ResponseEntity<String> putUsersPage(@RequestBody Balance balance) {
    balanceService.update(balance);
    ResponseMessage response = new ResponseMessage("Success", "200", balance);
    return ResponseEntity.status(HttpStatus.CREATED).body(response.toJSON());
  }

  /**
   * Метод для обработки запроса на удаление баланса пользователя по логину. Принимает логин
   * пользователя в качестве параметра, передает его сервису для выполнения операции удаления и
   * перенаправляет на страницу с балансами.
   *
   * @param userLogin Логин пользователя, чей баланс нужно удалить.
   * @return Ответ в формате JSON, содержащий статус операции, код состояния и логин удаленного пользователя.
   */
  @DeleteMapping("/balances/{userLogin}")
  public ResponseEntity<String> deleteUsersPage(@PathVariable String userLogin) {
    try {
      balanceService.deleteByUserLogin(userLogin);
    } catch (BalanceNotFoundException e) {
      ResponseMessage response = new ResponseMessage("Failed", "500", null);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response.toJSON());
    }
    ResponseMessage response = new ResponseMessage("Success", "200", userLogin);
    return ResponseEntity.status(HttpStatus.CREATED).body(response.toJSON());
  }
}
