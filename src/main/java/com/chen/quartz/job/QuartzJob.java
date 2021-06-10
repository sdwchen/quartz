package com.chen.quartz.job;

import org.quartz.*;
import org.springframework.scheduling.quartz.QuartzJobBean;

// 防止任务并发执行 @DisallowConcurrentExecution
@DisallowConcurrentExecution
public class QuartzJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobKey key = jobExecutionContext.getJobDetail().getKey();

        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        try {
            System.out.println("key:" + key);
            Thread.sleep(10000);

            System.out.println("key:" + key + "   " + "value:" + dataMap);
            // 可以再这里调用第三方接口，管理其他应用的定时任务
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
