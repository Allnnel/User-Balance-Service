package com.example.service;

import com.example.exception.CustomException;
import com.example.model.Balance;
import com.example.model.User;
import com.example.repository.BalanceRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BalanceServiceImpl implements BalanceService {
  private final BalanceRepository balanceRepository;
  private final UserService userService;

  @Autowired
  public BalanceServiceImpl(BalanceRepository balanceRepository, UserService userService) {
    this.balanceRepository = balanceRepository;
    this.userService = userService;
  }

  @Override
  public void save(Balance userBalance) throws CustomException {
    userService.findByLogin(userBalance.getUserLogin());
    Optional<Balance> optionalBalance =
        balanceRepository.findByUserLogin(userBalance.getUserLogin());
    if (optionalBalance.isPresent()) {
      throw new CustomException("Balance already exists.", 1);
    }
    balanceRepository.save(userBalance);
  }

  @Override
  public Balance findById(long id) throws CustomException {
    Optional<Balance> balanceOptional = balanceRepository.findById(id);
    return balanceOptional.orElseThrow(() -> new CustomException("Balance not found.", 2));
  }

  @Override
  public Balance findByUserLogin(String login) throws CustomException {
    Optional<Balance> balances = balanceRepository.findByUserLogin(login);
    if (!balances.isPresent()) {
      throw new CustomException("Balance not found.", 2);
    }
    return balances.get();
  }

  @Override
  public void delete(Balance balance) throws CustomException {
    Balance deleteBalance = findByUserLogin(balance.getUserLogin());
    balanceRepository.delete(deleteBalance);
  }

  @Override
  public void deleteById(long id) throws CustomException {
    Balance deleteBalance = findById(id);
    balanceRepository.delete(deleteBalance);
  }

  @Override
  public void deleteByUserLogin(String login) throws CustomException {
    Balance deleteBalance = findByUserLogin(login);
    balanceRepository.delete(deleteBalance);
  }

  @Override
  public List<Balance> getAllBalances() {
    return balanceRepository.findAll();
  }

  @Override
  public void update(Balance balance) throws CustomException {
    userService.findByLogin(balance.getUserLogin());
    Optional<Balance> optionalBalance = balanceRepository.findByUserLogin(balance.getUserLogin());
    if (optionalBalance.isPresent()) {
      Balance existingBalance = optionalBalance.get();
      existingBalance.setAmount(balance.getAmount());
      balanceRepository.saveAndFlush(existingBalance);
    } else {
      balanceRepository.save(balance);
    }
  }

  @Override
  public void increaseAmount(User user, int amountToAddOrSubtract) throws CustomException {
    Balance balance = findByUserLogin(user.getLogin());
    balance.increaseAmount(amountToAddOrSubtract);
    update(balance);
  }

  @Override
  public void decreaseAmount(User user, int amountToAddOrSubtract) throws CustomException {
    Balance balance = findByUserLogin(user.getLogin());
    balance.decreaseAmount(amountToAddOrSubtract);
    update(balance);
  }
}
