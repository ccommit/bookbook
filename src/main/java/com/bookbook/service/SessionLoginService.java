package com.bookbook.service;

import com.bookbook.dto.user.LoginUser;
import com.bookbook.dto.user.UserInfo;
import com.bookbook.exception.user.UserSessionLoginException;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class SessionLoginService {
    private static final String USER = "BOOKBOOK_USER";
    private final HttpSession httpSession;
    private static final Logger logger = LogManager.getLogger(UserService.class);

    public SessionLoginService(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    public void signIn(UserInfo userInfo) {
        try {
            httpSession.setAttribute(USER, new LoginUser(userInfo));
        } catch (Exception exception) {
            logger.error("세션 로그인 처리 중 오류가 발생했습니다.", exception);
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
