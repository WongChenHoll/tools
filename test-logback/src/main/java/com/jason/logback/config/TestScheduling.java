package com.jason.logback.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author WangChenHol
 * @date 2021-10-18 15:38
 **/
@Component
public class TestScheduling {

    private static final Logger logger = LoggerFactory.getLogger(TestScheduling.class);

    @Scheduled(cron = "0 0/1 * * * ?")
    @Async
    public void check5() throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        logger.info("每分钟执行一次，线程名：{}，执行时间：{}", threadName, getDate());

    }

    public String getDate() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
