package com.jason.jmockit.project.service;

import com.jason.jmockit.JunitWithJmockitApplication;
import com.jason.jmockit.project.model.User;
import mockit.Mocked;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 使用Mocked修饰一个接口。
 * 无论调用哪个方法，都是返回方法的默认值。其实被测试的方法是不走具体的逻辑的。
 *
 * @author WangChenHol
 * @date 2021-12-8 16:45
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JunitWithJmockitApplication.class)
public class UseMockedInterfaceTest {

    @Mocked
    private UserService userService;

    // boolean类型的返回false
    @Test
    public void test1() {
        Assert.assertFalse(userService.checkUser(new User("aaa", 20)));
        Assert.assertFalse(userService.checkUser(new User("aaa", 10)));
    }

    // int 类型的返回0
    @Test
    public void test2() {
        Assert.assertEquals(1, userService.updateUser(new User("aaa", 20)));
        Assert.assertEquals(1, userService.getUserAge(new User("aaa", 20)));
    }

    // String类型返回的是null
    @Test
    public void test3() {
        Assert.assertEquals("aaa", userService.getUserName(new User("aaa", 20)));
    }

    // 如果接口中该方法返回的不是原始类型也不是String类型，则返回的对象不为空，这个对象也是JMockit帮你实例化的，同样这个实例化的对象也是一个Mocked对象
    @Test
    public void test4() {
        Assert.assertNotNull(userService.getUser("haha")); // 正确
        Assert.assertNull(userService.getUser("haha")); // 错误
    }
}
