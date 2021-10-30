package com.jason.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author WangChenHol
 * @date 2021-10-18 15:12
 **/
@EnableScheduling
@SpringBootApplication
public class LogBackApplication {
    private static final Logger logger= LoggerFactory.getLogger(LogBackApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(LogBackApplication.class,args);
        logger.info("============================== 项目启动成功 =============================");
    }
}
