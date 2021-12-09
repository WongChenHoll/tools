package com.jason.jmockit.project.service.impl;

import com.jason.jmockit.hello.HelloJMockit;
import com.jason.jmockit.project.dao.UserDao;
import com.jason.jmockit.project.model.User;
import com.jason.jmockit.project.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WangChenHol
 * @date 2021-12-8 16:18
 **/
@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public void addUser(User user) {
        logger.info("===== addUser() =====");
        userDao.addUser(user);
    }

    @Override
    public User getUser(String name) {
        logger.info("===== getUser() =====");
        return userDao.getUser(name);
    }

    @Override
    public List<User> getUsers() throws Exception {
        logger.info("===== getUsers() =====");
        List<User> users = userDao.getUsers();
        if (users == null) {
            throw new Exception("用户不存在");
        }
        return users;
    }

    @Override
    public int updateUser(User user) {
        int other = getOther(user.getUserName());
        String s = HelloJMockit.staticMethod(user.getUserName());
        logger.info("===== updateUser() =====");
        String string = getUser(s).toString();
        return userDao.updateUser(user);
    }

    @Override
    public boolean checkUser(User user) {
        if (18 < user.getAge()) {
            return true;
        }
        logger.info("===== checkUser() =====");
        return false;
    }

    @Override
    public String getUserName(User user) {
        logger.info("===== getUserName() =====");
        return user.getUserName();
    }

    @Override
    public int getUserAge(User user) {
        logger.info("===== getUserAge() =====");
        return user.getAge();
    }

    private int getOther(String name) {
        return getUserAge(new User(name, 22));
    }
}
