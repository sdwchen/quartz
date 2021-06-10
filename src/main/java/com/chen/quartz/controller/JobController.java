package com.chen.quartz.controller;

import com.chen.quartz.entity.JobDetailVO;
import com.chen.quartz.job.QuartzJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class JobController {

    @Autowired
    private Scheduler scheduler;


    @GetMapping(value = "/addjob")
    public void addjob() throws Exception {

        Map<String, List<JobDetailVO>> map = param();
        List<JobDetailVO> list = map.get("test");
        for (JobDetailVO vo : list) {
            addJob(vo.getGroupName(), vo.getCronExpression());
        }

    }

    public void addJob(String jobGroupName, String cronExpression) throws Exception {
        System.out.println(jobGroupName);
        //构建job信息
        JobDetail jobDetail = JobBuilder.newJob(QuartzJob.class).withIdentity(jobGroupName, jobGroupName).usingJobData("jobSays", jobGroupName).build();
        //表达式调度构建器(即任务执行的时间)
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

        //按新的cronExpression表达式构建一个新的trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobGroupName, jobGroupName)
                .withSchedule(scheduleBuilder).build();
        try {
            // 添加job，但是并没有触发器，作业必须是“持久的”，如果不是，则抛出SchedulerException
           // scheduler.addJob(jobDetail,true);
            scheduler.scheduleJob(jobDetail,trigger);
        } catch (SchedulerException e) {
            System.out.println("创建定时任务失败" + e);
            throw new Exception("创建定时任务失败");
        }
    }

    public Map<String, List<JobDetailVO>> param() {
        Map<String, List<JobDetailVO>> map = new HashMap<>();
        List<JobDetailVO> list = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            JobDetailVO vo = new JobDetailVO();
            vo.setCronExpression("0/5 * *   * * ?");
            vo.setGroupName(String.valueOf(i));
            list.add(vo);
        }
        map.put("test", list);
        map.put("test1", list);
        return map;
    }

    @GetMapping(value = "/stopJob")
    public String stopJob(String name, String group) {
        System.out.println("中断job");
        // 中断其中一个群组任务
        JobKey jobKey = JobKey.jobKey(name, group);
        try {
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

        return "中断job成功";
    }

    @GetMapping(value = "/wakeJob")
    public String wakeJob(String name, String group) {
        System.out.println("启动job");
        JobKey jobKey = JobKey.jobKey(name, group);
        try {
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return "启动job成功";
    }

    @GetMapping(value = "/deleteJob")
    public String deleteJob(String name, String group) {
        System.out.println("删除job");
        JobKey jobKey = JobKey.jobKey(name, group);
        try {
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return "删除job成功";
    }


    @GetMapping(value = "/shutdown")
    public void shutdown() throws Exception {
        scheduler.shutdown(true);
    }

    @GetMapping(value = "/startJob")
    public String startJob(String name, String group) throws SchedulerException {
        System.out.println("立即启动job");
        JobKey jobKey = JobKey.jobKey(name, group);
      scheduler.triggerJob(jobKey);
      scheduler.start();
        return "启动job成功";
    }


}
