package com.jason.jmockit.hello;

import mockit.Mock;
import mockit.MockUp;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

/**
 * @author WangChenHol
 * @date 2021-12-11 16:02
 **/
public class MockUpTest {

    @Test
    public void test() {
        new MockUp<HashMap<String, Object>>(HashMap.class) {

            //使这个方法无论在什么情况下都不做任何处理
            @Mock
            public void clear() {
                return;
            }

            //使这个方法无论在什么情况下都返回true
            @Mock
            public boolean containsValue(Object value) {
                return true;
            }
        };
        Assert.assertTrue(new HashMap<String, Object>().containsValue("aa"));
    }
}
