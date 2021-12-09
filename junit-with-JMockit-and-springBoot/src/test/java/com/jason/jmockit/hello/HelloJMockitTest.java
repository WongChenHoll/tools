package com.jason.jmockit.hello;

import mockit.Expectations;
import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.assertEquals;

/**
 * @author WangChenHol
 * @date 2021-12-8 11:13
 **/
public class HelloJMockitTest {

    @Test
    public void testLocal() {
        new Expectations(Locale.class) {
            {
                Locale.getDefault();
                result = Locale.CHINA;
            }
        };
        assertEquals("这里是中国", new HelloJMockit().sayHello());
    }


}
