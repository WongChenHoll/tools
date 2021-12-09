package com.jason.jmockit.project.service;

import com.jason.jmockit.project.service.impl.OrderService;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import org.junit.Assert;
import org.junit.Test;

/**
 * 注解@Tested与@Injectable搭配使用.
 * <pre>
 *     注意：本测试类中使用了两种测试方法test1()和test2()。
 *     test1()中使用的被测试类实例和需要mock的类的实例是通过测试方法的参数传递进来的。而test2()测试方法使用的是全局变量实例。
 *     无论是哪一种必须保证来源一致。
 *     也就是不能使用全局的@Tested修饰的实例和测试方法参数中@Injectable修饰的实例一起使用。
 * </pre>
 *
 * @author WangChenHol
 * @date 2021-12-8 17:23
 **/
public class TestedAndInjectable {
    @Tested
    OrderService orderService;
    @Injectable
    MailService mailService;
    @Injectable
    UserCheckService userCheckService;

    //测试用户ID
    long testUserId = 123456L;
    //测试商品id
    long testItemId = 456789L;

    @Test
    public void test1(@Tested OrderService orderService, @Injectable MailService mailService, @Injectable UserCheckService userCheckService) {
        new Expectations() {
            {
                mailService.sendMail(testUserId, anyString);
                result = true;

                userCheckService.check(testUserId);
                result = true;
            }
        };
        Assert.assertTrue(orderService.submitOrder(testUserId, testItemId));
    }

    @Test
    public void test2() {
        new Expectations() {
            {
                mailService.sendMail(testUserId, anyString);
                result = true;

                userCheckService.check(testUserId);
                result = true;
            }
        };
        Assert.assertTrue(orderService.submitOrder(testUserId, testItemId));
    }
}
