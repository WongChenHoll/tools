package com.jason.sourceCode.use.project.controller;

import com.jason.sourceCode.use.model.User;
import com.jason.sourceCode.use.project.service.UserService;
import com.jason.sourceCode.use.project.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author WangChenHol
 * @date 2022-1-5 17:39
 **/
@RestController
@RequestMapping("/user")
public class UserControlle {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public void add() {
        System.out.println("controller层添加用户");
        userService.add();
    }

    @PostMapping("/update")
    public void update() {
        System.out.println("controller层修改用户");
        userService.update();
    }

    @GetMapping("/test")
    public void test(@RequestParam String name) {
        System.out.println("controller层test");
        UserServiceImpl aaa = new UserServiceImpl();
        aaa.test(new User());
    }
}
