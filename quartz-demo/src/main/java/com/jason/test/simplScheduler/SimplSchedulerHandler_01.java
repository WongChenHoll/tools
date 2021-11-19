package com.jason.test.simplScheduler;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 每一秒执行一次
 *
 * @author WangChenHol
 * @date 2021-11-16 17:34
 **/
public class SimplSchedulerHandler_01 {
    public static void main(String[] args) throws SchedulerException {
        // 创建调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        // 创建任务
        JobDetail job = JobBuilder.newJob(SimplSchedulerJob.class).withIdentity("simplScheduler")
                .usingJobData("handlerName", "SimplSchedulerHandler_01").build();

        // 创建触发器
        // 从启动开始，每一秒执行一次，会一直执行下去
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.repeatSecondlyForever();
        SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity("tets").startNow().withSchedule(simpleScheduleBuilder).build();

        // 关联
        scheduler.scheduleJob(job, trigger);
        scheduler.start();
    }

}
