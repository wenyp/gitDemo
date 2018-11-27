package com.example.demo.controller;

import com.example.demo.quartz.MyJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    //获得调度器实例
    StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();
    Scheduler scheduler = stdSchedulerFactory.getScheduler();

    public TestController() throws SchedulerException {
    }

    @GetMapping("/quartz")
    public String addQuartz(){

        try {

            //jobDetail
            JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
                    .withIdentity("myJob","Group")
                    .build();
            //触发器
            CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/11 * * * * ?"))
                    .withIdentity("MyTrigger", "CronGroup")
                    .build();

            scheduler.scheduleJob(jobDetail,cronTrigger);
            if (!scheduler.isStarted()){
                scheduler.start();
            }
            scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
            return "定时器创建失败";
        }
        return "定时器创建成功";
    }

}
