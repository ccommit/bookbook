package com.bookbook.util.password;

public interface Encoder {
    String hashPassword(final String rawPassword);

    boolean isMatched(final String rawPassword, final String hashedPassword);
}
