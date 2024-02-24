package com.example.response;

import com.example.model.Balance;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BalanceResponseMessage extends ResponseMessage {
  @JsonProperty("balance")
  private Object balance;

  public BalanceResponseMessage(String status, String message, String code, Object balance) {
    super(status, message, code);
    this.balance = balance;
  }

  public Object getBalance() {
    return balance;
  }

  public void setBalance(Balance balance) {
    this.balance = balance;
  }
}
