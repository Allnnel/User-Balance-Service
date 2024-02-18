package com.example.repository;

import com.example.model.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component("balanceRepository")
public interface BalanceRepository extends JpaRepository<Balance, Long> {
    Balance findById(long id);

    Balance findByUser_Id(long userId);

    Balance findByUser_Login(String login);

    void deleteById(long id);

    void deleteByUser_Id(long userId);

    void deleteByUser_Login(String login);
}

