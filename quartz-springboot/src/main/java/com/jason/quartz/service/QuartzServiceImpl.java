package com.jason.quartz.service;

import org.springframework.stereotype.Service;

/**
 * @author WangChenHol
 * @date 2021-11-18 11:07
 **/
@Service
public class QuartzServiceImpl implements QuartzService {

    @Override
    public void addJob(String jobName, String groupName, String cron) {

    }

    @Override
    public void deleteJob(String jobName, String groupName) {

    }

    @Override
    public void updateJob(String jobName, String groupName, String cron) {

    }

    @Override
    public boolean isExist(String jobName, String groupName) {
        return false;
    }

    @Override
    public void pauseJob(String jobName, String groupName) {

    }

    @Override
    public void pauseAllJobs() {

    }
}
