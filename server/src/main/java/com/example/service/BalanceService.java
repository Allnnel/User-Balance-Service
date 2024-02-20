package com.example.service;

import com.example.model.Balance;
import java.util.List;

public interface BalanceService {

  void save(Balance balance);

  void delete(Balance balance);

  Balance findById(long id);

  Balance findByUserLogin(String login);

  void deleteById(long id);

  void deleteByUserLogin(String login);

  List<Balance> getAllBalances();

  void update(Balance balance);
}
