package com.example.demo.quartz;

import com.example.demo.service.MyService;
import com.example.demo.service.impl.MyServiceImpl;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Job是无状态的任务，即指可以并发的任务，相互之间互不干扰
 *      在任务中有对于数据库的操作时，容易产生数据混乱：更新丢失，脏读，不可重复读，幻读等数据库兵法操作问题
 *
 *1）使用quartz【1.8.6】，任务类由原来实现Job，改为实现StatefulJob，该接口只是扩展了Job接口，执行过程中，使当前任务是有状态的，从而保证任务串行执行。
 *
 * 2）使用quartz【2.2.2】，在quartz2.0之后，提供了@DisallowConcurrentExecution注解，将这个注解加到job类上既可。
 *
 * 3）使用MethodInvokingJobDetailFactoryBean生成job，只需要配置属性concurrent=flase 即可保证线程中的任务执行完毕，才会创建新的线程
 *
 */

@Component
//@DisallowConcurrentExecution
public class MyJob implements Job {
    private static int count=1;

    @Autowired
    private MyService myService;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Trigger trigger = jobExecutionContext.getTrigger();
        if (trigger instanceof SimpleTrigger){
            System.out.println("SimpleTrigger内容:"+trigger.getJobKey());
        }else{
            String cronExpression = ((CronTrigger) trigger).getCronExpression();
            System.out.println("Cron表达式:"+cronExpression);
        }

        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+": 执行任务"+count+"次");
//        myService.test();
        if (myService==null){
            System.out.println("myService=null");
        }else {
            myService.test();
        }
        count++;
    }


}
