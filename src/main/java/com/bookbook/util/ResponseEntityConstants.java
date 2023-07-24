package com.bookbook.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseEntityConstants {
    public static final ResponseEntity<Void> RESPONSE_OK = new ResponseEntity(HttpStatus.OK);
}

