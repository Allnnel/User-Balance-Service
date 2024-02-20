package com.example.repository;

import com.example.model.Balance;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component("balanceRepository")
public interface BalanceRepository extends JpaRepository<Balance, Long> {
  Optional<Balance> findByUser_Login(String login);

  void deleteById(long id);

  void deleteByUser_Login(String login);
}
