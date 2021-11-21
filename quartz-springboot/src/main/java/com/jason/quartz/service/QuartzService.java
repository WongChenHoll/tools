package com.jason.quartz.service;

import com.jason.quartz.job.JobBean;
import org.quartz.Job;
import org.quartz.JobKey;
import org.quartz.SchedulerException;

import java.util.List;
import java.util.Map;

/**
 * @author WangChenHol
 * @date 2021-11-18 10:16
 **/
public interface QuartzService {

    void execute(JobBean jobBean) throws SchedulerException;

    /**
     * 添加一个任务job。任务名和触发器名保持一致，任务组名和触发器组名一致。
     *
     * @param jobClass  JobClassName
     * @param jobName   任务名
     * @param groupName 任务组名
     * @param cron      cron表达式
     * @throws SchedulerException 该异常表示添加任务失败
     */
    void addJob(Class<? extends Job> jobClass, String jobName, String groupName, String cron) throws SchedulerException;

    /**
     * 删除指定的某个任务job
     *
     * @param jobName   任务名
     * @param groupName 组名
     */
    void deleteJob(String jobName, String groupName) throws SchedulerException;

    /**
     * 按照组名删除整个组的任务
     *
     * @param groupName 组名
     */
    void deleteJobByGroup(String groupName) throws SchedulerException;

    /**
     * 注意：对于Job而言， 一个job可以对应多个Trigger，但对于Trigger而言，一个Trigger只能对应一个job
     *
     * @param triggerName  触发器名，一般情况下：触发器名字和任务名一样
     * @param triggerGroup 触发器组名，一般情况下：触发器组名和任务组名一样
     * @param cron         cron表达式
     */
    void updateJob(String triggerName, String triggerGroup, String cron) throws SchedulerException;

    boolean isExist(String jobName, String groupName) throws SchedulerException;

    boolean isExist(JobKey jobKey) throws SchedulerException;

    /**
     * 暂停某个任务
     *
     * @param jobName   任务名
     * @param groupName 组名
     */
    void pauseJob(String jobName, String groupName) throws SchedulerException;

    /**
     * 按照任务组暂停某些任务jon
     *
     * @param groupName 组名
     */
    void pauseJobByGroup(String groupName) throws SchedulerException;

    /**
     * 暂停所有任务
     */
    void pauseAllJobs() throws SchedulerException;

    /**
     * 重新启动某个任务
     *
     * @param jobName   任务名
     * @param groupName 组名
     */
    void resumeJob(String jobName, String groupName) throws SchedulerException;

    /**
     * 按照组名重启某些任务
     *
     * @param groupName 组名
     */
    void resumeJobByGroup(String groupName) throws SchedulerException;

    /**
     * 重亲启动所有的任务
     */
    void resumeAllJobs() throws SchedulerException;

    List<Map<String, String>> queryAllJob() throws SchedulerException;

}
