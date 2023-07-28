package com.bookbook.service;

import com.bookbook.dto.SignUpRequest;
import com.bookbook.exception.UserIdExistsException;
import com.bookbook.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public void signUp(SignUpRequest signUpRequest) {
        if (checkUniqueUserId(signUpRequest.getUserId())) {
            throw new UserIdExistsException("이미 가입된 아이디입니다. " + signUpRequest.getUserId());
        }
        signUpRequest.encryptPassword(signUpRequest.getPassword());
        userMapper.insertUser(signUpRequest);
    }

    private boolean checkUniqueUserId(String userId) {
        return userMapper.selectUserId(userId);
    }
}
