package com.example.demo.listener;

import com.example.demo.service.MyService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.quartz.listeners.JobListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyJobListener implements JobListener {

    @Autowired
    private MyService myService;
    /**
     * 不能返回null，否则为调度器添加监听器后，初始化时会报错
     * @return
     */
    @Override
    public String getName() {
        return MyJobListener.class.getSimpleName();
    }

    /**
     * Job执行前调用
     * @param jobExecutionContext
     */
    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
        System.out.println("----------------------------------------->"+getName()+"即将执行：");
        if (myService!=null){
            System.out.println("监听器注入服务：");
            myService.test();
        }else {
            System.out.println("myService=null");
        }
    }

    /**
     * Job即将执行却被TriggerListener否决时调用
     * @param jobExecutionContext
     */
    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {

    }

    /**
     *
     * Job执行完后调用
     * @param jobExecutionContext
     * @param e
     */
    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
        System.out.println("------------------------------------------>"+getName()+"执行完成:");

    }
}
