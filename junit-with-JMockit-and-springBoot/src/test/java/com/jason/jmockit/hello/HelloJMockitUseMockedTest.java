package com.jason.jmockit.hello;

import com.jason.jmockit.JunitWithJmockitApplication;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * @author WangChenHol
 * @date 2021-12-8 15:43
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JunitWithJmockitApplication.class)
public class HelloJMockitUseMockedTest {

    @Mocked
    private HelloJMockit helloJMockit;

    @Test
    public void testSayHello1() {
        new Expectations(HelloJMockit.class) {
            {
                helloJMockit.sayHello();
                result = "haha";
            }
        };
        assertEquals("haha", helloJMockit.sayHello());
    }

    @Test
    public void testSayHello2(@Mocked HelloJMockit helloJmockit2) {
        new Expectations() {
            {
                helloJmockit2.sayHello();
                result = "qqq";
            }
        };
        assertEquals("qqq", helloJmockit2.sayHello());
    }

    @Test
    public void test(){
        new Expectations(){
            {
                HelloJMockit.staticMethod("aaa");
                result="QQQ"; // 录制，无论该方法的参数是什么，都只返回“QQQ”
            }
        };
        assertEquals("QQQ",HelloJMockit.staticMethod("aaa")); // 通过
//        assertEquals("AAA",HelloJMockit.staticMethod("aaa")); // 不通过
//        assertEquals("QQQ",HelloJMockit.staticMethod("bbb")); // 不通过
    }

    /**
     * 当用@Mocked注解修饰一个类时，这个类中的静态方法、非静态方法都将返回一个null，当然自己new一个这个类时，调用方法返回的也是null。
     * 无论修饰的类是作为测试类的成员属性还是测试方法的参数。
     * 必须像上面的例子一样，使用Expectations或者MouckUp重新录制一下被测试的方法。
     */
    @Test
    public void teststaticMethod1() {
//        assertEquals("AAA",HelloJMockit.staticMethod("aaa"));

//        assertEquals("这里是中国",helloJMockit.sayHello());

//        HelloJMockit helloJMockit1 = new HelloJMockit();
//        assertEquals("这里是中国",helloJMockit1.sayHello());
    }

    @Test
    public void teststaticMethod2(@Mocked HelloJMockit helloJMockit1) {
//        assertEquals("AAA",HelloJMockit.staticMethod("aaa"));

//        assertEquals("这里是中国",helloJMockit1.sayHello());

//        HelloJMockit helloJMockit2 = new HelloJMockit();
//        assertEquals("这里是中国", helloJMockit2.sayHello());
    }


}
