package com.jason.quartz.job.jobs;

import com.jason.base.utils.DateUtil;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author WangChenHol
 * @date 2021-11-19 14:56
 **/
public class TestJob implements Job {
    private static final Logger logger = LoggerFactory.getLogger(TestJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        CronTrigger trigger = (CronTrigger) context.getTrigger();
        JobKey key = context.getJobDetail().getKey();
        logger.info("测试任务：{}.{},   执行时间：{}，     表达式：{}", key.getGroup(), key.getName(), DateUtil.currDateStr(), trigger.getCronExpression());
    }
}
