package com.example.exception;

public class BalanceNotFoundException extends RuntimeException {
  public BalanceNotFoundException() {
    super("Balance not found.");
  }
}
