package com.bookbook.interceptor;


import com.bookbook.annotation.UserLoginRequired;
import com.bookbook.dto.user.LoginUser;
import com.bookbook.exception.user.UnAuthenticatedException;
import com.bookbook.service.SessionLoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Objects;

@Component
@AllArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {
    private final SessionLoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod && ((HandlerMethod) handler).hasMethodAnnotation(UserLoginRequired.class)) {
            LoginUser loginUser = loginService.getSignedInUser();
            if (Objects.isNull(loginUser)) {
                throw new UnAuthenticatedException("로그인하지 않은 유저입니다.");
            }
        }
        return true;
    }
}
