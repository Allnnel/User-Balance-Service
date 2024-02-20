package com.example.service;

import com.example.exception.BalanceNotFoundException;
import com.example.exception.DuplicateBalanceException;
import com.example.model.Balance;
import com.example.model.User;
import com.example.repository.BalanceRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BalanceServiceImpl implements BalanceService {
  private final BalanceRepository balanceRepository;

  @Autowired
  public BalanceServiceImpl(@Qualifier("balanceRepository") BalanceRepository balanceRepository) {
    this.balanceRepository = balanceRepository;
  }

  @Override
  public void save(Balance userBalance) {
    if (balanceRepository.findByUser_Login(userBalance.getUser().getLogin()).isPresent()) {
      throw new DuplicateBalanceException();
    }
    balanceRepository.save(userBalance);
  }

  @Override
  public Balance findById(long id) {
    Optional<Balance> balanceOptional = balanceRepository.findById(id);
    return balanceOptional.orElseThrow(BalanceNotFoundException::new);
  }

  @Override
  public Balance findByUserLogin(String login) {
    Optional<Balance> balanceOptional = balanceRepository.findByUser_Login(login);
    return balanceOptional.orElseThrow(BalanceNotFoundException::new);
  }

  @Override
  public void delete(Balance balance) {
    findById(balance.getId());
    balanceRepository.delete(balance);
  }

  @Override
  public void deleteById(long id) {
    findById(id);
    balanceRepository.deleteById(id);
  }

  @Override
  public void deleteByUserLogin(String login) {
    findByUserLogin(login);
    balanceRepository.deleteByUser_Login(login);
  }

  @Override
  public List<Balance> getAllBalances() {
    return balanceRepository.findAll();
  }

  @Override
  public void update(Balance balance) {
    Optional<Balance> optionalBalance = balanceRepository.findById(balance.getId());
    if (optionalBalance.isPresent()) {
      Balance existingBalance = optionalBalance.get();
      existingBalance.setAmount(balance.getAmount());
      if (balance.getUser() != null) existingBalance.setUser(balance.getUser());
    } else {
      balanceRepository.save(balance);
    }
  }

  @Override
  public void increaseAmount(User user, int amountToAddOrSubtract) {
    Balance balance = findByUserLogin(user.getLogin());
    balance.increaseAmount(amountToAddOrSubtract);
    update(balance);
  }

  @Override
  public void decreaseAmount(User user, int amountToAddOrSubtract) {
    Balance balance = findByUserLogin(user.getLogin());
    balance.decreaseAmount(amountToAddOrSubtract);
    update(balance);
  }
}
