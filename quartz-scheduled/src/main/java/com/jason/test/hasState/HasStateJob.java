package com.jason.test.hasState;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;

/**
 * 有状态的Job
 *
 * @author WangChenHol
 * @date 2021-11-16 16:22
 **/
@PersistJobDataAfterExecution // 添加此注解，声明此job是个有状态的job，多次Job调用期间可以持有一些状态信息
public class HasStateJob implements Job {

    private Integer times;

    public void setTimes(Integer times) {
        this.times = times;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        times++;
        System.out.println("执行次数：" + times);

        // 将times值存放在JobDataMap中进行持久化
        context.getJobDetail().getJobDataMap().put("times", times);
    }
}
