package com.jason.test.simplScheduler;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 每5秒执行一次，总共执行三次,注意和类SimplSchedulerHandler_04中的区别
 *
 * @author WangChenHol
 * @date 2021-11-16 17:34
 **/
public class SimplSchedulerHandler_03 {
    public static void main(String[] args) throws SchedulerException {
        // 创建调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        // 创建任务
        JobDetail job = JobBuilder.newJob(SimplSchedulerJob.class).withIdentity("simplScheduler")
                .usingJobData("handlerName", "SimplSchedulerHandler_03").build();

        // 创建触发器
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.repeatSecondlyForTotalCount(3,5);
        SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity("tets").startNow().withSchedule(simpleScheduleBuilder).build();

        // 关联
        scheduler.scheduleJob(job, trigger);
        scheduler.start();
    }

}
