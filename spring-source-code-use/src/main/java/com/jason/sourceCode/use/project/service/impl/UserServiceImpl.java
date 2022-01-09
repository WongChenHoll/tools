package com.jason.sourceCode.use.project.service.impl;

import com.jason.sourceCode.use.model.User;
import com.jason.sourceCode.use.project.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author WangChenHol
 * @date 2022-1-5 17:38
 **/
@Service
public class UserServiceImpl implements UserService {
    @Override
    public void add() {
        User user = new User();
        user.setName("aaa");
        System.out.println("service层添加用户");
    }

    @Transactional
    @Override
    public void update() {
        System.out.println("service修改用户");
    }

    @Override
    public void test(User user) {
        System.out.println("service层test");
    }
}
