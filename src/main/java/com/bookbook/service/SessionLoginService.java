package com.bookbook.service;

import com.bookbook.dto.user.LoginUser;
import com.bookbook.dto.user.UserInfo;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class SessionLoginService {
    private static final String USER = "BOOKBOOK_USER";
    private final HttpSession httpSession;

    public SessionLoginService(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    public void signIn(UserInfo userInfo) {
        httpSession.setAttribute(USER, new LoginUser(userInfo));
    }

    public void logout() {
        httpSession.removeAttribute(USER);
    }

    public LoginUser getSignedInUser() {
        return (LoginUser) httpSession.getAttribute(USER);
    }
}
