package com.example.service;

import com.example.exception.BalanceDeletionException;
import com.example.exception.BalanceNotFoundException;
import com.example.model.Balance;
import com.example.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component("balanceService")
public class BalanceServiceImpl implements BalanceService {
    private BalanceRepository balanceRepository;

    @Autowired
    public BalanceServiceImpl(@Qualifier("balanceRepository") BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    @Override
    public Balance save(Balance userBalance) {
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
        balanceRepository.delete(balance);
        if (balanceRepository.findById(balance.getId()) != null) {
            throw new BalanceDeletionException();
        }
    }

    @Override
    public void deleteById(long id) {
        balanceRepository.deleteById(id);
        if (balanceRepository.findById(id) != null) {
            throw new BalanceDeletionException();
        }
    }

    @Override
    public void deleteByUserId(long userId) {
        balanceRepository.deleteByUser_Id(userId);
        if (balanceRepository.findByUser_Id(userId) != null) {
            throw new BalanceDeletionException();
        }
    }

    @Override
    public void deleteByUserLogin(String login) {
        balanceRepository.deleteByUser_Login(login);
        if (balanceRepository.findByUser_Login(login) != null) {
            throw new BalanceDeletionException();
        }
    }


}
