package com.example.service;

import com.example.model.Balance;

public interface BalanceService  {

    void save(Balance balance);
    void delete(Balance balance);

    Balance findById(long id);
    Balance findByUserId(long userId);
    Balance findByUserLogin(String login);

    void deleteById(long id);
    void deleteByUserId(long userId);
    void deleteByUserLogin(String login);
}
