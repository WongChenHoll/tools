package com.jason.quartz.service;

import com.jason.quartz.job.JobBean;
import com.jason.quartz.job.JobEnum;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author WangChenHol
 * @date 2021-11-18 11:07
 **/
@Service
public class QuartzServiceImpl implements QuartzService {

    private static final Logger logger = LoggerFactory.getLogger(QuartzServiceImpl.class);

    @Autowired
    private Scheduler scheduler;

    @Override
    public void execute(JobBean jobBean) throws SchedulerException {
        Class<? extends Job> jobClass = JobEnum.getJobClass(jobBean.getType());
        addJob(jobClass, jobBean.getJobName(), jobBean.getGroupName(), jobBean.getCron());
    }

    @Override
    public void addJob(Class<? extends Job> jobClass, String jobName, String groupName, String cron) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, groupName).build();
        CronTrigger trigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule(cron)).withIdentity(jobName, groupName).build();
        scheduler.scheduleJob(jobDetail, trigger);
        logger.info("添加一个任务：{}.{}", groupName, jobName);
    }

    @Override
    public void deleteJob(String jobName, String groupName) throws SchedulerException {
        assertJob(jobName, groupName);

        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, groupName);
        // 停止触发器
        scheduler.pauseTrigger(triggerKey);
        // 移除触发器
        scheduler.unscheduleJob(triggerKey);
        // 删除任务
        scheduler.deleteJob(JobKey.jobKey(jobName, groupName));
        logger.info("删除任务：{}.{}", groupName, jobName);

    }

    @Override
    public void deleteJobByGroup(String groupName) throws SchedulerException {
        GroupMatcher<JobKey> groupMatcher = GroupMatcher.groupEquals(groupName);
        Set<JobKey> jobKeys = scheduler.getJobKeys(groupMatcher);
        scheduler.deleteJobs(new ArrayList<>(jobKeys));
        logger.info("删除某个任务组下所有的任务：{}", groupName);
    }

    @Override
    public void updateJob(String triggerName, String triggerGroup, String cron) throws SchedulerException {
        assertJob(triggerName, triggerGroup);

        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroup);
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(CronScheduleBuilder.cronSchedule(cron)).build();
        scheduler.rescheduleJob(triggerKey, trigger);
        logger.info("修改触发器：{}.{}的任务时间", triggerGroup, triggerName);
    }

    @Override
    public boolean isExist(String jobName, String groupName) throws SchedulerException {
        return scheduler.checkExists(JobKey.jobKey(jobName, groupName));
    }

    @Override
    public boolean isExist(JobKey jobKey) throws SchedulerException {
        return scheduler.checkExists(jobKey);
    }

    @Override
    public void pauseJob(String jobName, String groupName) throws SchedulerException {
        assertJob(jobName, groupName);
        scheduler.pauseJob(JobKey.jobKey(jobName, groupName));
        logger.info("任务：{}.{}暂停......", groupName, jobName);
    }

    @Override
    public void pauseJobByGroup(String groupName) throws SchedulerException {
        scheduler.pauseJobs(GroupMatcher.groupEquals(groupName));
        logger.info("暂停{}该组的任务", groupName);
    }

    @Override
    public void pauseAllJobs() throws SchedulerException {
        scheduler.pauseAll();
        logger.info("暂停所有的任务");

    }

    @Override
    public void resumeJob(String jobName, String groupName) throws SchedulerException {
        assertJob(jobName, groupName);
        scheduler.resumeJob(JobKey.jobKey(jobName, groupName));
        logger.info("重新启动任务：{}.{} ......", groupName, jobName);
    }

    @Override
    public void resumeJobByGroup(String groupName) throws SchedulerException {
        scheduler.resumeJobs(GroupMatcher.groupEquals(groupName));
        logger.info("重新[{}]该组的任务", groupName);
    }

    @Override
    public void resumeAllJobs() throws SchedulerException {
        scheduler.resumeAll();
        logger.info("重新启动所有的任务");
    }

    @Override
    public List<Map<String, String>> queryAllJob() throws SchedulerException {
        ArrayList<Map<String, String>> allJobs = new ArrayList<>();
        Map<String, String> job;
        // TODO
        List<String> triggerGroupNames = scheduler.getTriggerGroupNames();
        for (String triggerGroupName : triggerGroupNames) {
            Set<TriggerKey> triggerKeys = scheduler.getTriggerKeys(GroupMatcher.groupEquals(triggerGroupName));
            for (TriggerKey triggerKey : triggerKeys) {
                job = new HashMap<>();
                job.put("trigger", triggerKey.toString());
                JobKey jobKey = scheduler.getTrigger(triggerKey).getJobKey();
                job.put("job", jobKey.toString());

                SchedulerContext context = scheduler.getContext();

                allJobs.add(job);
            }
        }
        return allJobs;
    }

    private void assertJob(JobKey jobKey) throws SchedulerException {
        if (!isExist(jobKey)) {
            throw new SchedulerException("该任务[" + jobKey.getGroup() + "." + jobKey.getName() + "]不存在");
        }
    }

    private void assertJob(String jobName, String groupName) throws SchedulerException {
        if (!isExist(jobName, groupName)) {
            throw new SchedulerException("该任务[" + groupName + "." + jobName + "]不存在");
        }
    }
}
