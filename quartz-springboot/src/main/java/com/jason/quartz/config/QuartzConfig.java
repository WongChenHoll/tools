package com.jason.quartz.config;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * @author WangChenHol
 * @date 2021-11-19 14:06
 **/
@SpringBootConfiguration
public class QuartzConfig {

    @Autowired
    private DataSource druidDataSource;

    /**
     * 读取quartz.properties配置文件，初始化配置参数，将参数保存在Properties中。
     */
    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean properties = new PropertiesFactoryBean();
        properties.setLocation(new ClassPathResource("quartz.properties"));
        properties.afterPropertiesSet();
        return properties.getObject();
    }

    /**
     * 将配置文件参数值加载到SchedulerFactoryBean中。
     */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setQuartzProperties(quartzProperties());
        schedulerFactoryBean.setDataSource(druidDataSource);
        return schedulerFactoryBean;
    }

    /**
     * 获取Scheduler
     */
    @Bean
    public Scheduler scheduler() throws IOException {
        return schedulerFactoryBean().getScheduler();
    }
}
