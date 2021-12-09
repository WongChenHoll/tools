package com.jason.jmockit.hello;

import com.jason.jmockit.JunitWithJmockitApplication;
import mockit.Expectations;
import mockit.Injectable;
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
public class HelloJMockitUseInjectableTest {

    @Injectable
    private HelloJMockit helloJMockit;

    // 类中的静态方法可以使用。
    @Test
    public void test1() {
        assertEquals("AAA", HelloJMockit.staticMethod("aaa"));
    }

    // 类中的非静态方法不可用，始终返回null，必须使用Expectations重新录制，也就是Mock方法
    @Test
    public void test2() {
//        assertEquals("这里是中国",helloJMockit.sayHello());
        new Expectations(){
            {
                helloJMockit.sayHello();
                result="haha";
            }
        };
        assertEquals("haha",helloJMockit.sayHello());
    }

    // 可以自己new一个该类的实例，然后调用类中的方法
    @Test
    public void test3(){
        assertEquals("这里是中国",new HelloJMockit().sayHello());

    }

    // 在方法参数中使用@Injectable，调用类中的静态方法，不受影响
    @Test
    public void test4(@Injectable HelloJMockit helloJMockit) {
        assertEquals("AAA", HelloJMockit.staticMethod("aaa"));
    }

    // 在方法参数中使用@Injectable，同test2()测试方法一样。
    @Test
    public void test5(@Injectable HelloJMockit helloJMockit1){
//        assertEquals("这里是中国",helloJMockit1.sayHello()); // 始终返回null，必须重新录制，也就是mock，如下：
        new Expectations(){
            {
                helloJMockit1.sayHello();
                result="哈哈哈";
            }
        };
        assertEquals("哈哈哈",helloJMockit1.sayHello());
    }


}
