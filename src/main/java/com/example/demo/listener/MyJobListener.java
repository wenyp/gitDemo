package com.example.demo.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.quartz.listeners.JobListenerSupport;
import org.springframework.stereotype.Component;

@Component
public class MyJobListener implements JobListener {
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
