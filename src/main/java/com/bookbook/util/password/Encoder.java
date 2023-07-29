package com.bookbook.util.password;

public interface Encoder {
    String hashPassword(String rawPassword);

    boolean isMatched(String rawPassword, String hashedPassword);
}
