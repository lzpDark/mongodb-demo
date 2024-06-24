package com.example.mongodbdemo.config;

import com.example.mongodbdemo.interceptor.TestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(new TestInterceptor());
        interceptorRegistration.addPathPatterns("/**");
        interceptorRegistration.pathMatcher(new AntPathMatcher());
    }
}
