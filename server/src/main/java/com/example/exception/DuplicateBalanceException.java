package com.example.exception;

public class DuplicateBalanceException extends RuntimeException {
    public DuplicateBalanceException() {
        super("Duplicate balance exception.");
    }
}
