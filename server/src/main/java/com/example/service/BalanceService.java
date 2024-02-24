package com.example.service;

import com.example.exception.CustomException;
import com.example.model.Balance;
import com.example.model.User;
import java.util.List;

public interface BalanceService {

  void save(Balance balance) throws CustomException;

  void delete(Balance balance) throws CustomException;

  Balance findById(long id) throws CustomException;

  Balance findByUserLogin(String login) throws CustomException;

  void deleteById(long id) throws CustomException;

  void deleteByUserLogin(String login) throws CustomException;

  List<Balance> getAllBalances();

  void update(Balance balance) throws CustomException;

  void decreaseAmount(User user, int amountToAddOrSubtract) throws CustomException;

  void increaseAmount(User user, int amountToAddOrSubtract) throws CustomException;
}
