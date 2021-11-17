package com.jason.test.simplScheduler;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 每 5 秒执行一次
 *
 * @author WangChenHol
 * @date 2021-11-16 17:34
 **/
public class SimplSchedulerHandler_02 {
    public static void main(String[] args) throws SchedulerException {
        // 创建调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        // 创建任务
        JobDetail job = JobBuilder.newJob(SimplSchedulerJob.class).withIdentity("simplScheduler")
                .usingJobData("handlerName", "SimplSchedulerHandler_02").build();

        // 创建触发器
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.repeatSecondlyForever(5);
        SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity("tets").startNow().withSchedule(simpleScheduleBuilder).build();

        // 关联
        scheduler.scheduleJob(job, trigger);
        scheduler.start();
    }

}
