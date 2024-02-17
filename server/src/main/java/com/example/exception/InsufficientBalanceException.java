package com.example.exception;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException() {
        super("Error: Insufficient balance.");
    }
}
