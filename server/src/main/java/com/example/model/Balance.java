package com.example.model;

import com.example.exception.CustomException;
import javax.persistence.*;

@Entity
@Table(name = "balance", schema = "server")
public class Balance {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "amount", nullable = false)
  private double amount;

  //  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "userLogin", nullable = false)
  private String userLogin;

  public Balance(String userLogin, double amount) {
    this.userLogin = userLogin;
    this.amount = amount;
  }

  public Balance(String userLogin) {
    this.userLogin = userLogin;
    this.amount = 0.0;
  }

  public Balance() {}

  public void increaseAmount(double value) {
    this.amount += value;
  }

  public void decreaseAmount(double value) throws CustomException {
    if (this.amount >= value) {
      this.amount -= value;
    } else {
      throw new CustomException("Insufficient balance.", 5);
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

  public String getUserLogin() {
    return userLogin;
  }

  public void setUserLogin(String userLogin) {
    this.userLogin = userLogin;
  }

  @Override
  public String toString() {
    return "Balance{" + "id=" + id + ", amount=" + amount + ", userLogin=" + userLogin + '}';
  }
}
