package com.jason.quartz.controller;

import com.jason.base.response.BaseResponse;
import com.jason.quartz.job.JobBean;
import com.jason.quartz.service.QuartzService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author WangChenHol
 * @date 2021-11-18 10:34
 **/
@RestController
@RequestMapping("/operation")
public class QuartzController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private QuartzService quartzService;

    @GetMapping("/test")
    public BaseResponse<Object> test() {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from TEST_TAB_FIRST");
        return BaseResponse.success(maps);
    }

    // 新增
    @PostMapping("/addJob")
    public BaseResponse<String> addJob(@RequestBody JobBean jobBean) throws SchedulerException {
        quartzService.execute(jobBean);
        return BaseResponse.success();
    }

    // 修改
    @PostMapping("/update")
    public BaseResponse<String> update(@RequestBody JobBean jobBean) throws SchedulerException {
        quartzService.updateJob(jobBean.getJobName(), jobBean.getGroupName(), jobBean.getCron());
        return BaseResponse.success();
    }

    // 暂停
    @GetMapping("/pause")
    public BaseResponse<String> pause(@RequestParam String jobName, @RequestParam String groupName) throws SchedulerException {
        quartzService.pauseJob(jobName, groupName);
        return BaseResponse.success();
    }

    // 暂停整个组任务
    @GetMapping("/pauseGroup")
    public BaseResponse<String> pauseGroup(@RequestParam String groupName) throws SchedulerException {
        quartzService.pauseJobByGroup(groupName);
        return BaseResponse.success();
    }

    // 重启
    @GetMapping("/resume")
    public BaseResponse<String> resume(@RequestParam String jobName, @RequestParam String groupName) throws SchedulerException {
        quartzService.resumeJob(jobName, groupName);
        return BaseResponse.success();
    }

    // 重启整个组任务
    @GetMapping("/resumeGroup")
    public BaseResponse<String> resumeGroup(@RequestParam String groupName) throws SchedulerException {
        quartzService.resumeJobByGroup(groupName);
        return BaseResponse.success();
    }

    @GetMapping("/all")
    public BaseResponse<Object> all() throws SchedulerException {
        return BaseResponse.success(quartzService.queryAllJob());
    }

}
