package com.bookbook.config;

import com.bookbook.argumentresolver.LoginArgumentResolver;
import com.bookbook.interceptor.AdminPermissionInterceptor;
import com.bookbook.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final LoginInterceptor loginInterceptor;
    private final AdminPermissionInterceptor adminPermissionInterceptor;
    private final LoginArgumentResolver loginArgumentResolver;

    public WebConfig(LoginInterceptor loginInterceptor, AdminPermissionInterceptor adminPermissionInterceptor, LoginArgumentResolver loginArgumentResolver) {
        this.loginInterceptor = loginInterceptor;
        this.adminPermissionInterceptor = adminPermissionInterceptor;
        this.loginArgumentResolver = loginArgumentResolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor);
        registry.addInterceptor(adminPermissionInterceptor);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(loginArgumentResolver);
    }
}