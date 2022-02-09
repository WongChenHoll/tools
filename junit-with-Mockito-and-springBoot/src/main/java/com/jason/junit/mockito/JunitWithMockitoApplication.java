package com.jason.junit.mockito;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author WangChenHol
 * @date 2021-12-7 22:06
 **/
@SpringBootApplication
public class JunitWithMockitoApplication {
    private static final Logger logger = LoggerFactory.getLogger(JunitWithMockitoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(JunitWithMockitoApplication.class, args);
        logger.info("============================= 项目JunitWithMockitoApplication启动成功 ==================================");
    }
}
