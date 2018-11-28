package com.example.demo.controller;

import com.example.demo.quartz.MyJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private static final String DEFAULT_CRON_GROUP="Cron_Group";
    private static final String DEFAULT_SIMPLE_GROUP="SimpleGroup";
    private static final String DEFAULT_GROUP="DefaultGroup";

    private static final String DEFAULT_TRIGGER="MyTrigger";
    private static final String DEFAULT_JOB="MyJob";

    //获得调度器实例

//    private Scheduler scheduler=new StdSchedulerFactory().getScheduler();

    public TestController() throws SchedulerException {
    }
    @Autowired
    private Scheduler scheduler;


    @GetMapping("/quartz")
    public String addQuartz(){

        try {

            //jobDetail
            JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
                    .withIdentity(DEFAULT_JOB,DEFAULT_GROUP)
                    .build();
            //触发器
            CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                    .withSchedule(CronScheduleBuilder.cronSchedule("0,10,20,30,40,50 * * * * ?"))
                    .withIdentity(DEFAULT_TRIGGER, DEFAULT_CRON_GROUP)
                    .build();

            SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .repeatForever()
                            .withIntervalInSeconds(5)
                            .withRepeatCount(10))
                    .withIdentity(DEFAULT_TRIGGER,DEFAULT_SIMPLE_GROUP)
                    .build();

            scheduler.scheduleJob(jobDetail,simpleTrigger);





            scheduler.start();

        } catch (SchedulerException e) {
            e.printStackTrace();
            return "定时器创建失败";
        }
        return "定时器创建成功";
    }

}
