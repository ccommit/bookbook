package com.bookbook.exception.user;

public class UserUpdatingException extends RuntimeException {
    public UserUpdatingException(String message, Throwable cause) {
        super(message, cause);
    }
}
