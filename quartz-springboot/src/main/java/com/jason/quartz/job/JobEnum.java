package com.jason.quartz.job;

import com.jason.quartz.job.jobs.DataBaseJob;
import com.jason.quartz.job.jobs.FileJob;
import com.jason.quartz.job.jobs.TestJob;
import org.quartz.Job;

/**
 * 任务类型
 *
 * @author WangChenHol
 * @date 2021-11-21 14:21
 **/
public enum JobEnum {
    TEST("test", TestJob.class, "测试用的任务"),
    DATABASE("database", DataBaseJob.class, "数据库操作相关任务"),
    FILE("file", FileJob.class, "文件操作相关任务");

    private final String type; // 任务类型
    private final Class<? extends Job> jobClass; // 任务类
    private final String describe; // 任务描述

    JobEnum(String type, Class<? extends Job> jobClass, String describe) {
        this.type = type;
        this.jobClass = jobClass;
        this.describe = describe;
    }

    public static Class<? extends Job> getJobClass(String type) {
        for (JobEnum value : JobEnum.values()) {
            if (value.type.equals(type)) {
                return value.jobClass;
            }
        }
        return null;
    }

    public String getType() {
        return type;
    }

    public Class<? extends Job> getJobClass() {
        return jobClass;
    }

    public String getDescribe() {
        return describe;
    }
}
