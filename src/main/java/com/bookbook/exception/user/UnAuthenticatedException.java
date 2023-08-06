package com.bookbook.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnAuthenticatedException extends RuntimeException {
    public UnAuthenticatedException(String message) {
        super(message);
    }
}
