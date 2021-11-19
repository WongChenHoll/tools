package com.jason.test.simplScheduler;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 每5秒执行一次，总共执行三次,注意和类SimplSchedulerHandler_03中的区别
 *
 * @author WangChenHol
 * @date 2021-11-16 17:34
 **/
public class SimplSchedulerHandler_04 {
    public static void main(String[] args) throws SchedulerException {
        // 创建调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        // 创建任务
        JobDetail job = JobBuilder.newJob(SimplSchedulerJob.class).withIdentity("simplScheduler")
                .usingJobData("handlerName", "SimplSchedulerHandler_04").build();

        // 创建触发器
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.repeatSecondlyForever(5).withRepeatCount(2);
        SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity("tets").startNow().withSchedule(simpleScheduleBuilder).build();

        // 关联
        scheduler.scheduleJob(job, trigger);
        scheduler.start();
    }

}
