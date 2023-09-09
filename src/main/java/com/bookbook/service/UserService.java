package com.bookbook.service;

import com.bookbook.dto.user.*;
import com.bookbook.exception.user.*;
import com.bookbook.mapper.UserMapper;
import com.bookbook.util.password.Encoder;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
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
        try {
            userMapper.insertUser(signUpRequest);
        } catch (Exception exception) {
            throw new UserRegistrationException("회원가입 중 오류가 발생했습니다.", exception);
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
            throw new UserFindingException("유저 프로필 조회 중 오류가 발생했습니다.", exception);
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
            throw new UserDeletingException("유저 회원 탈퇴 중 오류가 발생했습니다.", exception);
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
            throw new UserDeletingException("관리자에 의한 회원 탈퇴 처리 중 오류가 발생했습니다.", exception);
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
            throw new UserUpdatingException("비밀번호 수정 중 오류가 발생했습니다.", exception);
        }
    }

    public void updateIntroduce(LoginUser loginUser, IntroduceUpdateRequest introduceUpdateRequest) {
        loginUser.updateIntroduce(introduceUpdateRequest.getIntroduce());

        try {
            userMapper.updateIntroduce(loginUser);
        } catch (Exception exception) {
            throw new UserUpdatingException("introduce 정보 수정 중 오류가 발생했습니다.", exception);
        }
    }

    private boolean checkUniqueUserId(String userId) {
        try {
            return userMapper.existsByUserId(userId);
        } catch (Exception exception) {
            throw new UserIdDuplicationException("Id 값이 유일한 값인지 확인 중 오류가 발생했습니다", exception);
        }
    }
}
