package com.jason.test.hasState;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author WangChenHol
 * @date 2021-11-16 16:24
 **/
public class HasStateHandler {
    public static void main(String[] args) throws SchedulerException {

        // 创建调度器 Scheduler
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        // 创建任务 JobDetail
        JobDetail job = JobBuilder.newJob(HasStateJob.class).withIdentity("测试有状态任务").usingJobData("times", 0).build();

        // 创建触发器 Trigger ，每5秒执行一次
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("有状态触发器").startNow().withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(5)).build();

        scheduler.scheduleJob(job, trigger);
        scheduler.start();

    }
}
