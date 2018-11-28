package com.example.demo.config;

import com.example.demo.listener.MyJobListener;
import org.quartz.JobKey;
import org.quartz.Matcher;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.Properties;

@Configuration
public class SchedulerConfig {
    @Autowired
    private MyJobFactory myJobFactory;

    @Autowired
    private MyJobListener myJobListener;

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        //设置读取的配置文件
        factoryBean.setQuartzProperties(quartzProperties());

        factoryBean.setOverwriteExistingJobs(true);

        //必须设置，不然Job类无法自动注入service等
        factoryBean.setJobFactory(myJobFactory);
        //设置Scheduler工厂类用于持久化到数据库
        factoryBean.setSchedulerFactory(new StdSchedulerFactory());


        return factoryBean;
    }
    @Bean(name="scheduler")
    public Scheduler scheduler() throws IOException, SchedulerException {
//        return StdSchedulerFactory.getDefaultScheduler();
        Scheduler scheduler = schedulerFactoryBean().getScheduler();


        //注入监听器
        scheduler.getListenerManager().addJobListener(myJobListener, new Matcher<JobKey>() {
            @Override
            public boolean isMatch(JobKey jobKey) {
                if (jobKey.getName().equals("MyJob") && jobKey.getGroup().equals("DefaultGroup")){
                    return true;
                }
                return false;
            }
        });
        return scheduler;
    }


    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        return propertiesFactoryBean.getObject();
    }

    @Bean
//    监听器可以监听到工程的启动，在工程停止再启动时可以让已有的定时任务继续进行
    public QuartzInitializerListener executorListener(){
        return new QuartzInitializerListener();
    }
}
