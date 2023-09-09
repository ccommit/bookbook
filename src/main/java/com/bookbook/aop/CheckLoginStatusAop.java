package com.bookbook.aop;

import com.bookbook.dto.user.LoginUser;
import com.bookbook.dto.user.UserRole;
import com.bookbook.exception.user.UnAuthorizedUserException;
import com.bookbook.exception.user.UserForbiddenException;
import com.bookbook.service.SessionLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
@Log4j2
public class CheckLoginStatusAop {

    private final SessionLoginService loginService;

    @Before(value = "@annotation(CheckLoginStatus) && @annotation(checkLoginStatus)")
    public void checkStatus(CheckLoginStatus checkLoginStatus) {
        UserRole auth = checkLoginStatus.auth();
        switch (auth) {
            case USER -> userLoginStatus();
            case ADMIN -> adminLoginStatus();
            default -> {}
        }
    }

    public LoginUser userLoginStatus() {
        LoginUser signedInUser = loginService.getSignedInUser();
        if(signedInUser == null) {
            throw new UnAuthorizedUserException("로그인하지 않은 유저입니다.") {};
        }
        return signedInUser;
    }

    public void adminLoginStatus() {
        LoginUser loginUser = userLoginStatus();
        if(loginUser.getUserRole() != UserRole.ADMIN) {
            throw new UserForbiddenException(loginUser.getId() + "는 관리자가 아닙니다.") {};
        }
    }
}
