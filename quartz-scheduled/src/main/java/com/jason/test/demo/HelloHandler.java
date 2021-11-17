package com.jason.test.demo;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author WangChenHol
 * @date 2021-11-16 14:27
 **/
public class HelloHandler {
    public static void main(String[] args) throws SchedulerException {
        // 创建一个触发器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        //创建Job实例
        JobDetail job = JobBuilder.newJob(HelloJob.class)
                .withIdentity("jobName1", "jobGroup1")
                .build();

        // 创建触发器
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("triggerName1", "triggerName1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(5))
                .build();

        scheduler.scheduleJob(job, trigger);
        scheduler.start();
    }
}
