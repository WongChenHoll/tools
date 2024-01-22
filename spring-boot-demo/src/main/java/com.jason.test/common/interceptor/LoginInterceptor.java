package com.jason.test.common.interceptor;

import com.jason.test.common.constans.BaseConstans;
import com.jason.test.common.constans.UserInfo;
import com.jason.test.project.model.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.concurrent.TimeUnit;

/**
 * @author ChenHol.Wong
 * @create 2020/8/2 18:18
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    private final RedisTemplate<String, Object> redisTemplate;

    public LoginInterceptor(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        ValueOperations operations = redisTemplate.opsForValue();
        HttpSession session = request.getSession();
        String key = BaseConstans.REDIS_KEY_LOGIN_INFO + session.getId();
        User attribute = (User) session.getAttribute(BaseConstans.LOGIN_SESSION_ATTRIBUTE);
        operations.set(key, attribute);
        redisTemplate.expire(key, 1000 * 60, TimeUnit.MILLISECONDS); // 根据最后的操作时间，设定session过期时间为一分钟
        UserInfo.setUserNameThreadLocal(attribute);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserInfo.clear();
    }
}
