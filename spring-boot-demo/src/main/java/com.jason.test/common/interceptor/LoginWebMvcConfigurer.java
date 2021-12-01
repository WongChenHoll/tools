package com.jason.test.common.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ChenHol.Wong
 * @create 2020/8/2 18:20
 */
@Configuration
public class LoginWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor(redisTemplate)).addPathPatterns("/test-jason/login");
        registry.addInterceptor(new RequestInterceptor(redisTemplate)).addPathPatterns("/**").excludePathPatterns("/test-jason/login");
    }
}
