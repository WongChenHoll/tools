package annotation.config;

import annotation.bean.MyDataSource;
import annotation.bean.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author WangChenHol
 * @date 2021-9-22 15:13
 **/
@Configuration
public class TestConfiguration {

    @Bean
    public User testConfigurationUser() {
        User user = new User();
        user.setAge(35);
        user.setName("test-Configuration");
        return user;
    }

    @Bean
    public MyDataSource otherDataSource() {
        return new MyDataSource("Other", "jdbc:other://localhost:0000/test");
    }
}
