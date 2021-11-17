package com.jason.test.simplScheduler;

import com.jason.base.utils.DateUtil;
import org.quartz.*;

/**
 * @author WangChenHol
 * @date 2021-11-16 17:29
 **/
public class SimplSchedulerJob implements Job {

    private String handlerName;

    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("SimplScheduler Job, the scheduler is " + handlerName + ",  nowTime:" + DateUtil.currDateStr());

        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        System.out.println("jobDataMap:" + jobDataMap.getString("handlerName"));

        JobKey jobKey = context.getJobDetail().getKey();
        System.out.println("任务名：" + jobKey.getName() + "  任务组名：" + jobKey.getGroup());

        System.out.println(context.getFireInstanceId());
    }
}
