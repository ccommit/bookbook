package com.bookbook.exception;

public class NicknameExistsException extends RuntimeException {
    public NicknameExistsException(String message) {
        super(message);
    }
}
