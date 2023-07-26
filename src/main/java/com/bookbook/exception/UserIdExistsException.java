package com.bookbook.exception;

public class UserIdExistsException extends RuntimeException {
    public UserIdExistsException(String message) {
        super(message);
    }
}
