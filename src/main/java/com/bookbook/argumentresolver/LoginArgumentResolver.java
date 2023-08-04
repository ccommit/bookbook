package com.bookbook.argumentresolver;

import com.bookbook.annotation.IfLogin;
import com.bookbook.dto.user.LoginUser;
import com.bookbook.service.SessionLoginService;
import com.bookbook.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@AllArgsConstructor
public class LoginArgumentResolver implements HandlerMethodArgumentResolver {
    private final SessionLoginService loginService;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(IfLogin.class)
                && parameter.getParameterType().equals(LoginUser.class);
    }

    @Override
    public LoginUser resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        try {
            return loginService.getSignedInUser();
        } catch (Exception exception) {
            logger.error(exception.getMessage());
            throw new RuntimeException(exception);
        }
    }
}
