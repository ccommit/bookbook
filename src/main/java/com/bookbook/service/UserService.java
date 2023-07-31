package com.bookbook.service;

import com.bookbook.dto.SignUpRequest;
import com.bookbook.exception.UserIdExistsException;
import com.bookbook.mapper.UserMapper;
import com.bookbook.util.password.Encoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserMapper userMapper;
    private final Encoder passwordEncoder;

    public UserService(UserMapper userMapper, Encoder passwordEncoder) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public void signUp(SignUpRequest signUpRequest) {
        if (checkUniqueUserId(signUpRequest.getUserId())) {
            throw new UserIdExistsException("이미 가입된 아이디입니다. " + signUpRequest.getUserId());
        }
        signUpRequest.updateHashedPassword(encryptPassword(signUpRequest.getPassword()));
        userMapper.insertUser(signUpRequest);
    }

    public String encryptPassword(String password) {
        return passwordEncoder.hashPassword(password);
    }

    private boolean checkUniqueUserId(String userId) {
        return userMapper.selectUserId(userId);
    }
}
