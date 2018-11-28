package com.example.demo.listener;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;
import org.springframework.stereotype.Component;

@Component
public class MyTriggerListener implements TriggerListener {
    @Override
    public String getName() {
        return MyTriggerListener.class.getSimpleName();
    }

    /**
     * 触发器被触发，将要执行Job时
     * @param trigger
     * @param jobExecutionContext
     */
    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext jobExecutionContext) {
        System.out.println("》》》触发器触发了:"+trigger.getKey());
    }

    /**
     * 触发器触发，即将要执行Job，TriggerListener给了选择是否执行Job
     * 返回true：不会执行Job内容
     * @param trigger
     * @param jobExecutionContext
     * @return
     */
    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext jobExecutionContext) {
        return false;
    }

    /**
     * Trigger错过触发时调用
     * @param trigger
     */
    @Override
    public void triggerMisfired(Trigger trigger) {
        System.out.println("Trigger错过执行");
    }

    /**
     * 当前Trigger触发成功且Job执行完成一次时调用
     * @param trigger
     * @param jobExecutionContext
     * @param completedExecutionInstruction
     */
    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext jobExecutionContext, Trigger.CompletedExecutionInstruction completedExecutionInstruction) {
        System.out.println("》》》触发器完成");
    }
}
