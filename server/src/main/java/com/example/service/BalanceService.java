package com.example.service;

import com.example.exception.BalanceNotFoundException;
import com.example.exception.DuplicateBalanceException;
import com.example.model.Balance;
import com.example.model.User;
import java.util.List;

public interface BalanceService {

  void save(Balance balance) throws DuplicateBalanceException;

  void delete(Balance balance) throws BalanceNotFoundException;

  Balance findById(long id) throws BalanceNotFoundException;

  Balance findByUserLogin(String login) throws BalanceNotFoundException;

  void deleteById(long id) throws BalanceNotFoundException;

  void deleteByUserLogin(String login) throws BalanceNotFoundException;

  List<Balance> getAllBalances();

  void update(Balance balance);

  void decreaseAmount(User user, int amountToAddOrSubtract) throws BalanceNotFoundException;

  void increaseAmount(User user, int amountToAddOrSubtract) throws BalanceNotFoundException;
}
