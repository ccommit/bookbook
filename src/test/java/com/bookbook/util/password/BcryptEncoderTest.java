package com.bookbook.util.password;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BcryptEncoderTest {

    private final BcryptEncoder encoder = new BcryptEncoder();

    @DisplayName("BCrypt 에 의해 비밀번호가 정상적으로 해싱 처리된다.")
    @Test
    void passwordIsHashedByBCrypt() {
        String password = "test-password";
        String hashed = encoder.hashPassword(password);

        assertThat(hashed).hasSize(60);
        assertTrue(hashed.startsWith("$2a$10$"));
    }

    @DisplayName("패스워드 입력값이 같으면 해싱 결과값이 동일하다.")
    @Test
    void isMatched() {
        String password = "test-password";
        assertTrue(encoder.isMatched(password, BCrypt.hashpw(password, BCrypt.gensalt())));
    }

    @DisplayName("패스워드 입력값이 다르면 해싱 결과값이 동일하지 않다.")
    @Test
    void isNotMatchedWithDifferentValue() {
        String one = "one-password";
        String another = "another-password";
        assertFalse(encoder.isMatched(another, BCrypt.hashpw(one, BCrypt.gensalt())));
    }
}