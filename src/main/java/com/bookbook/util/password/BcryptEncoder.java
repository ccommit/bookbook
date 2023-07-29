package com.bookbook.util.password;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class BcryptEncoder implements Encoder {
    @Override
    public String hashPassword(final String rawPassword) {
        return BCrypt.hashpw(rawPassword, BCrypt.gensalt());
    }

    @Override
    public boolean isMatched(final String rawPassword, final String hashedPassword) {
        return BCrypt.checkpw(rawPassword, hashedPassword);
    }
}
