package com.jason.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ChenHol.Wong
 * @create 2020/7/26 21:11
 */
//@MapperScan("com.jason.test.project")
@SpringBootApplication
public class TestApplication {

    public static final Logger logger = LoggerFactory.getLogger(TestApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
        logger.info("====================== 服务启动成功 ======================");
    }
}
