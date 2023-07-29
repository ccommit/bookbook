package com.bookbook.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class UserIdExistsException extends RuntimeException {
    public UserIdExistsException(String message) {
        super(message);
    }
}
