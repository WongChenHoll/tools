package com.jason.test.cronScheduler;

import com.jason.base.utils.DateUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author WangChenHol
 * @date 2021-11-17 14:50
 **/
public class CronJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String cron = context.getTrigger().getJobDataMap().getString("cron");
        System.out.println("使用Cron表达式启用任务，表达式为：" + cron + ",    时间：" + DateUtil.currDateStr());
    }
}
