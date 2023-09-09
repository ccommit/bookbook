package com.bookbook.exception.user;

public class UserDeletingException extends RuntimeException {
    public UserDeletingException(String message, Throwable cause) {
        super(message, cause);
    }
}
