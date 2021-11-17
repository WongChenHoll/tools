package com.jason.test.demo;

import com.jason.base.utils.DateUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 自定义一个Job
 *
 * @author WangChenHol
 * @date 2021-11-16 14:25
 **/
public class HelloJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("清理垃圾，时间：" + DateUtil.currDateStr());
    }

}
