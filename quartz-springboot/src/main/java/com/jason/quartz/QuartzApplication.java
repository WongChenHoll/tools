package com.jason.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author WangChenHol
 * @date 2021-11-18 14:13
 **/
@SpringBootApplication
public class QuartzApplication {
    private static final Logger logger = LoggerFactory.getLogger(QuartzApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(QuartzApplication.class, args);
        logger.info("============================= Quartz 服务启动完成 =============================");
    }
}
