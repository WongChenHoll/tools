package com.jason.quartz.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * @author WangChenHol
 * @date 2021-11-19 14:41
 **/
@SpringBootConfiguration
public class DataSourceConfig {


    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }
}
