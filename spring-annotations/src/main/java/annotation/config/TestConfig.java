package annotation.config;

import annotation.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

/**
 * @author WangChenHol
 * @date 2021-9-14 16:49
 **/
@SpringBootConfiguration
public class TestConfig {
    public static final Logger logger = LoggerFactory.getLogger(TestConfig.class);

    public TestConfig() {
        logger.info("初始化配置类容器");
    }

    @Bean
    @Scope("singleton")
    public User singletonUser() {
        User user = new User();
        user.setAge(20);
        user.setName("singleton-user(zhangSan)");
        return user;
    }

    @Bean
    @Scope("prototype")
    public User prototypeUser() {
        User user = new User();
        user.setAge(20);
        user.setName("prototype-user(zhangSan)");
        return user;
    }

    @Bean
    @RequestScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
    public User requestUser() {
        User user = new User();
        user.setAge(20);
        user.setName("zhangSan");
        return user;
    }

    @Bean
    @SessionScope
    public User sessionUser() {
        User user = new User();
        user.setAge(20);
        user.setName("zhangSan");
        return user;
    }
}
