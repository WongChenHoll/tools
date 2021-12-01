package com.jason.test;

import com.jason.test.project.model.User;
import com.jason.test.project.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author WangChenHol
 * @date 2021-12-1 16:52
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestApplication.class})
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void testlogin() {
        User login = userService.login("1");
        Assert.assertTrue(true);
    }

}
