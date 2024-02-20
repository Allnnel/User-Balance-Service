package com.example.exception;

public class BalanceDeletionException extends RuntimeException {
  public BalanceDeletionException() {
    super("The balance cannot be deleted.");
  }
}
