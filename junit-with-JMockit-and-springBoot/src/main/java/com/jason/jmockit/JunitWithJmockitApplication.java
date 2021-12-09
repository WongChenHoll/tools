package com.jason.jmockit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author WangChenHol
 * @date 2021-12-8 10:58
 **/
@SpringBootApplication
public class JunitWithJmockitApplication {
    private static final Logger logger = LoggerFactory.getLogger(JunitWithJmockitApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(JunitWithJmockitApplication.class, args);
        logger.info("============================= 项目JunitWithJmockitApplication启动成功 ==================================");
    }
}
