package com.example.exception;

/**
 * Класс для пользовательского исключения, используемого для обработки различных ситуаций.
 * Если баланс не найден, выбрасывает исключение.
 *  "Balance already exists." - code: 1.
 *  "Balance not found." - code: 2.
 *  "User already exists." - code: 3.
 *  "User not found." - code: 4.
 *  "Insufficient balance." - code: 5.
 */
public class CustomException extends Exception {
    private final int errorCode;
    private final String message;
    public CustomException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

