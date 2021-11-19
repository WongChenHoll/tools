package com.jason.quartz.service;

/**
 * @author WangChenHol
 * @date 2021-11-18 10:16
 **/
public interface QuartzService {

    void addJob(String jobName, String groupName, String cron);

    void deleteJob(String jobName, String groupName);

    void updateJob(String jobName, String groupName, String cron);

    boolean isExist(String jobName, String groupName);

    /**
     * 暂停某个任务
     *
     * @param jobName   任务名
     * @param groupName 组名
     */
    void pauseJob(String jobName, String groupName);

    /**
     * 暂停所有任务
     */
    void pauseAllJobs();
}
