package com.bookbook.interceptor;


import com.bookbook.annotation.AdminLoginRequired;
import com.bookbook.dto.user.LoginUser;
import com.bookbook.exception.UnAuthenticatedException;
import com.bookbook.exception.UnAuthorizedUserException;
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
public class AdminPermissionInterceptor implements HandlerInterceptor {
    private final SessionLoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod && ((HandlerMethod) handler).hasMethodAnnotation(AdminLoginRequired.class)) {
            LoginUser loginUser = loginService.getSignedInUser();
            if (Objects.isNull(loginUser)) {
                throw new UnAuthenticatedException("로그인하지 않은 유저입니다.");
            }
            if (!loginUser.isAdmin()) {
                throw new UnAuthorizedUserException("어드민 계정이 아닙니다.");
            }
        }
        return true;
    }
}
