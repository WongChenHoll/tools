package com.jason.test.project.service.impl;

import com.jason.test.project.dao.TestDao;
import com.jason.test.project.model.User;
import com.jason.test.project.service.RequestLogService;
import com.jason.test.project.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 添加用户
 *
 * @author ChenHol.Wong
 * @create 2020/7/28 21:17
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    public static final String KEY = "test:spring-boot:user";

    @Autowired
    private TestDao testDao;

    @Autowired
    private RequestLogService requestLogService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 此方法用于测试事务有效性
     *
     * @param user
     * @return
     */
    @Transactional
    @Override
    public int addUser(User user) {
        testDao.addUser(user);
        requestLogService.addLog("/jason/test-jason/test");
        return 1;
    }

    @Override
    public User login(String id) {
        User user = testDao.queryUserById(id);
        return user;
    }

}
