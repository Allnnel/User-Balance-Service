package com.example.model;
import javax.persistence.*;

import com.example.exception.InsufficientBalanceException;

@Entity
@Table(name = "balance", schema = "server")
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "amount", nullable = false)
    private double amount;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "userLogin", referencedColumnName = "login"),
            @JoinColumn(name = "userId", referencedColumnName = "id")
    })
    private User user;



    public Balance(User user) {
        this.user = user;
        this.amount = 0.0;
    }

    public Balance(User user, double amount) {
        this.user = user;
        this.amount = amount;
    }

    // увелечение баланса
    public void increaseAmount(double value) {
        this.amount += value;
    }

    // уменьшение баланса
    public void decreaseAmount(double value) {
        if (this.amount >= value) {
            this.amount -= value;
        } else {
            throw new InsufficientBalanceException();
        }
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Balance{" +
                "id=" + id +
                ", amount=" + amount +
                ", user=" + user +
                '}';
    }
}
