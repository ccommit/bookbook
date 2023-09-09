package com.bookbook.service;

import com.bookbook.dto.user.LoginUser;
import com.bookbook.dto.user.UserInfo;
import com.bookbook.exception.user.UserSessionLoginException;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class SessionLoginService {
    private final String USER = "BOOKBOOK_USER";
    private final HttpSession httpSession;

    public SessionLoginService(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    public void signIn(UserInfo userInfo) {
        try {
            httpSession.setAttribute(USER, new LoginUser(userInfo));
        } catch (Exception exception) {
            throw new UserSessionLoginException("세션 로그인 처리 중 오류가 발생했습니다.", exception);
        }
    }

    public void logout() {
        httpSession.removeAttribute(USER);
    }

    public LoginUser getSignedInUser() {
        return (LoginUser) httpSession.getAttribute(USER);
    }
}
