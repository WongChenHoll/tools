package annotation.config;

import annotation.bean.DruidDataSource;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;


/**
 * @author WangChenHol
 * @date 2021-9-22 16:39
 **/
@SpringBootConfiguration
public class DruidDataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid.read")
    public DruidDataSource readDruidDataSource() {
        return new DruidDataSource();
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.druid.write")
    public DruidDataSource writeDruidDataSource() {
        return new DruidDataSource();
    }
}
