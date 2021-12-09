package com.jason.jmockit.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * @author WangChenHol
 * @date 2021-12-8 11:10
 **/
@Component
public class HelloJMockit {

    private static final Logger logger = LoggerFactory.getLogger(HelloJMockit.class);

    // 测试普通方法
    public String sayHello() {
        Locale locale = Locale.getDefault();
        if (locale.equals(Locale.CHINA)) {
            return "这里是中国";
        } else {
            return "出国啦";
        }
    }

    // 测试静态方法
    public static String staticMethod(String v) {
        logger.info("===== staticMethod() =====");
        return v.toUpperCase();
    }
}
