package com.bookbook.service;

import com.bookbook.dto.SignUpRequest;
import com.bookbook.exception.UserIdExistsException;
import com.bookbook.mapper.UserMapper;
import com.bookbook.util.password.Encoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final Encoder encoder;

    public UserService(UserMapper userMapper, Encoder encoder) {
        this.userMapper = userMapper;
        this.encoder = encoder;
    }

    public void signUp(SignUpRequest signUpRequest) {
        String userId = signUpRequest.getUserId();

        if (checkUniqueUserId(userId)) {
            throw new UserIdExistsException("이미 가입된 아이디입니다. " + userId);
        }

        signUpRequest = SignUpRequest.createSignUpRequest(
                userId,
                encoder.hashPassword(signUpRequest.getPassword()),
                signUpRequest.getIntroduce()
        );

        userMapper.insertUser(signUpRequest);
    }

    private boolean checkUniqueUserId(String userId) {
        return userMapper.selectUserId(userId);
    }
}
