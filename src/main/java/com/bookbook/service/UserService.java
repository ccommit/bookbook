package com.bookbook.service;

import com.bookbook.dto.user.*;
import com.bookbook.exception.user.UserIdExistsException;
import com.bookbook.exception.user.UserIdNotExistsException;
import com.bookbook.exception.user.UserLoginException;
import com.bookbook.exception.user.UserWrongPasswordException;
import com.bookbook.mapper.UserMapper;
import com.bookbook.util.password.Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserMapper userMapper;
    private final Encoder passwordEncoder;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserMapper userMapper, Encoder passwordEncoder) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public void signUp(SignUpRequest signUpRequest) {
        if (checkUniqueUserId(signUpRequest.getUserId())) {
            throw new UserIdExistsException("이미 가입된 아이디입니다. " + signUpRequest.getUserId());
        }
        signUpRequest.updateHashedPassword(encryptPassword(signUpRequest.getPassword()));
        try {
            userMapper.insertUser(signUpRequest);
        } catch (Exception exception) {
            logger.error(exception.getMessage());
            throw new RuntimeException(exception);
        }
    }

    public UserInfo checkLoginInfo(LoginRequest loginRequest) {
        UserInfo userInfo = userMapper.findByActiveUserId(loginRequest.getUserId());
        if (userInfo == null || !passwordEncoder.isMatched(loginRequest.getPassword(), userInfo.getHashedPassword())) {
            throw new UserLoginException("등록되지 않은 아이디이거나 아이디 또는 비밀번호를 잘못 입력했습니다.");
        }
        return userInfo;
    }

    public UserProfileResponse findUserProfile(LoginUser loginUser) {
        try {
            return userMapper.findUserProfile(loginUser);
        } catch (Exception exception) {
            logger.error(exception.getMessage());
            throw new RuntimeException(exception);
        }
    }

    public String encryptPassword(String password) {
        return passwordEncoder.hashPassword(password);
    }

    public void deleteUserOfMine(LoginUser loginUser, WithdrawalRequest withdrawalRequest) {
        if (!passwordEncoder.isMatched(withdrawalRequest.getPassword(), loginUser.getHashedPassword())) {
            throw new UserWrongPasswordException("비밀번호를 다시 입력해주세요.");
        }

        try {
            userMapper.deleteUser(loginUser.getId());
        } catch (Exception exception) {
            logger.error(exception.getMessage());
            throw new RuntimeException(exception);
        }
    }

    public void deleteUser(String userId) {
        UserInfo userInfo = userMapper.findByActiveUserId(userId);
        if (userInfo == null) {
            throw new UserIdNotExistsException("가입된 유저의 아이디가 아닙니다." + userId);
        }

        try {
            userMapper.deleteUser(userInfo.getId());
        } catch (Exception exception) {
            logger.error(exception.getMessage());
            throw new RuntimeException(exception);
        }
    }

    public void updatePassword(LoginUser loginUser, PasswordUpdateRequest passwordUpdateRequest) {
        if (!passwordEncoder.isMatched(passwordUpdateRequest.getOldPassword(), loginUser.getHashedPassword())) {
            throw new UserWrongPasswordException("비밀번호를 다시 입력해주세요.");
        }
        loginUser.updateHashedPassword(encryptPassword(passwordUpdateRequest.getNewPassword()));

        try {
            userMapper.updatePassword(loginUser);
        } catch (Exception exception) {
            logger.error(exception.getMessage());
            throw new RuntimeException(exception);
        }
    }

    public void updateIntroduce(LoginUser loginUser, IntroduceUpdateRequest introduceUpdateRequest) {
        loginUser.updateIntroduce(introduceUpdateRequest.getIntroduce());

        try {
            userMapper.updateIntroduce(loginUser);
        } catch (Exception exception) {
            logger.error(exception.getMessage());
            throw new RuntimeException(exception);
        }
    }

    private boolean checkUniqueUserId(String userId) {
        try {
            return userMapper.existsByUserId(userId);
        } catch (Exception exception) {
            logger.error(exception.getMessage());
            throw new RuntimeException(exception);
        }
    }
}
