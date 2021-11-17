package com.jason.test.cronScheduler;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author WangChenHol
 * @date 2021-11-17 14:57
 **/
public class CronSchedulerHandler {
    public static void main(String[] args) throws SchedulerException {
        // 创建调度器 Scheduler
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        // 创建任务 JobDetail
        JobDetail job = JobBuilder.newJob(CronJob.class).withIdentity("sec-job", "test-group").build();

        // 创建触发器Trigger
        String cron = "0/5 * * * * ?";
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("cron-trigger").withSchedule(cronScheduleBuilder).usingJobData("cron", cron).build();

        scheduler.scheduleJob(job, trigger);
        scheduler.start();
    }
}
