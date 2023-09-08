package com.bookbook.argumentresolver;

import com.bookbook.dto.user.LoginUser;
import com.bookbook.exception.user.LoginArgumentResolverException;
import com.bookbook.service.SessionLoginService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Log4j2
@Component
@AllArgsConstructor
public class LoginArgumentResolver implements HandlerMethodArgumentResolver {
    private final SessionLoginService loginService;

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
            throw new LoginArgumentResolverException("로그인한 유저가 맞는지 확인하는 중 오류가 발생했습니다.", exception);
        }
    }
}
