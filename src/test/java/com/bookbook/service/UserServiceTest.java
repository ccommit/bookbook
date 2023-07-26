package com.bookbook.service;

import com.bookbook.dto.SignUpRequest;
import com.bookbook.exception.UserIdExistsException;
import com.bookbook.util.password.Encoder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private Encoder passwordEncoder;

    SignUpRequest user;

    @BeforeEach
    void setUpUser() {
        String nickname = "wisdom";
        String password = "pwpwpwpw123";
        String introduce = "introduce";
        user = SignUpRequest.createSignUpRequest(
                nickname,
                passwordEncoder.hashPassword(password),
                introduce
        );
    }

    @DisplayName("유저가 정상적으로 회원가입에 성공합니다")
    @Test
    void signUp(){
        userService.signUp(user);
        assertThat(user.getUserId()).isNotNull();
    }

    @DisplayName("이미 가입된 아이디 입력으로 회원가입에 실패합니다")
    @Test
    void test(){
        assertThatThrownBy(() -> userService.signUp(user))
                .isInstanceOf(UserIdExistsException.class)
                .hasMessageContaining("이미 가입된 아이디입니다.")
                .hasMessageContaining(user.getUserId())
        ;
    }
}