package com.bookbook.mapper;

import com.bookbook.dto.user.SignUpUserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@MybatisTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    SignUpUserDTO user;

    @BeforeEach
    void setUpUser() {
        String nickname = "wisdom";
        String password = "pwpwpwpw123";
        String introduce = "introduce";
        user = SignUpUserDTO.createUserDTO(
                nickname,
                password,
                introduce
        );
    }

    @DisplayName("테스트 목적으로 유저를 등록합니다.")
    @Test
    void InsertUser(){
        Long isInserted = userMapper.insertUser(user);
        assertThat(isInserted).isEqualTo(1);
    }
}

