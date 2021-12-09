package com.jason.jmockit.project.service;

import com.jason.jmockit.JunitWithJmockitApplication;
import com.jason.jmockit.project.dao.UserDao;
import com.jason.jmockit.project.model.User;
import com.jason.jmockit.project.service.impl.UserServiceImpl;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * 模拟日常项目中Service层的单元测试
 *
 * @author WangChenHol
 * @date 2021-12-8 16:20
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JunitWithJmockitApplication.class)
public class UserServiceTest {

    @Tested
    private UserServiceImpl userService; // 这里必须是类，不能是接口

    @Injectable
    private UserDao userDao;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    User user = new User("zhangsan", 20);

    @Test
    public void test1() {
        new Expectations() {
            {
                userDao.addUser(user);
                result = null;
            }
        };
        userService.addUser(user);
    }

    @Test
    public void test2() {
        new Expectations() {
            {
                userDao.getUser("aa");
                result = user;
            }
        };
        assertEquals(user, userService.getUser("aa"));
    }

    @Test
    public void test3() {
        new Expectations() {
            {
                userDao.updateUser(user);
                result = 10;
            }
        };
        assertEquals(10, userService.updateUser(user));
    }

    @Test
    public void test4() throws Exception {
        ArrayList<User> list = new ArrayList<>();
        list.add(new User("ddd", 33));
        new Expectations() {
            {
                userDao.getUsers();
                result = list;
            }
        };
        assertEquals(1, userService.getUsers().size());

        // 测试抛出异常
        expectedException.expect(Exception.class);
        expectedException.expectMessage("用户不存在");
        new Expectations() {
            {
                userDao.getUsers();
                result = null;
            }
        };
        assertNull(userService.getUsers());
    }
}
