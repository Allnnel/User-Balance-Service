package com.example.service;

import com.example.exception.BalanceDeletionException;
import com.example.exception.BalanceNotFoundException;
import com.example.exception.DuplicateBalanceException;
import com.example.model.Balance;
import com.example.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component("balanceService")
public class BalanceServiceImpl implements BalanceService {
    private final BalanceRepository balanceRepository;

    @Autowired
    public BalanceServiceImpl(@Qualifier("balanceRepository") BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    @Override
    public Balance save(Balance userBalance) {
        if(balanceRepository.findById(userBalance.getId()) != null) {
            throw new DuplicateBalanceException();
        }
        Balance savedUserBalance = balanceRepository.save(userBalance);
        if (savedUserBalance == null) {
            throw new BalanceNotFoundException();
        }
        return savedUserBalance;
    }

    @Override
    public Balance findById(long id) {
        Balance balance = balanceRepository.findById(id);
        if (balance == null) {
            throw new BalanceNotFoundException();
        }
        return balance;
    }

    @Override
    public Balance findByUserId(long id) {
        Balance balance = balanceRepository.findByUser_Id(id);
        if (balance == null) {
            throw new BalanceNotFoundException();
        }
        return balance;
    }

    @Override
    public Balance findByUserLogin(String login) {
        Balance balance = balanceRepository.findByUser_Login(login);
        if (balance == null) {
            throw new BalanceNotFoundException();
        }
        return balance;
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
    public void deleteByUserId(long userId) {
        findByUserId(userId);
        balanceRepository.deleteByUser_Id(userId);
    }

    @Override
    public void deleteByUserLogin(String login) {
        findByUserLogin(login);
        balanceRepository.deleteByUser_Login(login);
    }

}
