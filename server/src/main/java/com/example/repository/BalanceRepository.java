package com.example.repository;

import com.example.model.Balance;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {
  Optional<Balance> findByUserLogin(String login);

  void deleteById(long id);

  void deleteByUserLogin(String login);
}
