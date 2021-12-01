package com.jason.test.project.service;

import com.jason.test.project.model.User;

/**
 * @author ChenHol.Wong
 * @create 2020/7/27 21:15
 */
public interface UserService {
    int addUser(User user);

    User login(String id);
}
