package com.example.controller;

import com.example.exception.CustomException;
import com.example.model.Balance;
import com.example.response.BalanceResponseMessage;
import com.example.response.ResponseMessage;
import com.example.service.BalanceService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class BalanceController {

  private final BalanceService balanceService;

  @Autowired
  public BalanceController(BalanceService balanceService) {
    this.balanceService = balanceService;
  }

  @GetMapping("/balances")
  public ResponseEntity<ResponseMessage> getUsersPage() {
    List<Balance> balances = balanceService.getAllBalances();
    if (balances.isEmpty()) {
      ResponseMessage response =
          new BalanceResponseMessage("Failed", "The table is empty.", "500", null);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
    ResponseMessage response = new BalanceResponseMessage("Success", null, "200", balances);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PostMapping("/balances")
  public ResponseEntity<ResponseMessage> postUsersPage(@RequestBody Balance balance) {
    try {
      balanceService.save(balance);
    } catch (CustomException e) {
      ResponseMessage response = new BalanceResponseMessage("Failed", e.getMessage(), "500", null);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
    ResponseMessage response = new BalanceResponseMessage("Success", null, "200", balance);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PutMapping("/balances")
  public ResponseEntity<ResponseMessage> putUsersPage(@RequestBody Balance balance) {
    try {
      balanceService.update(balance);
    } catch (CustomException e) {
      ResponseMessage response = new BalanceResponseMessage("Failed", e.getMessage(), "500", null);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
    ResponseMessage response = new BalanceResponseMessage("Success", null, "200", balance);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @DeleteMapping("/balances")
  public ResponseEntity<ResponseMessage> deleteUsersPage(@RequestBody Balance balance) {
    try {
      balanceService.delete(balance);
    } catch (CustomException e) {
      ResponseMessage response = new BalanceResponseMessage("Failed", e.getMessage(), "500", null);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
    ResponseMessage response = new BalanceResponseMessage("Success", null, "200", balance);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }
}
