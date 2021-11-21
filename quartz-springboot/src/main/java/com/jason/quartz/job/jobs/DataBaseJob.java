package com.jason.quartz.job.jobs;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author WangChenHol
 * @date 2021-11-21 14:25
 **/
public class DataBaseJob implements Job {
    private static final Logger logger = LoggerFactory.getLogger(DataBaseJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        CronTrigger trigger = (CronTrigger) context.getTrigger();
        JobKey key = context.getJobDetail().getKey();
        logger.info("======================= {}.{} cron={} =======================", key.getGroup(), key.getName(), trigger.getCronExpression());
    }
}
