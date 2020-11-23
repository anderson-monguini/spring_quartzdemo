package com.quartz.component;

import java.util.Calendar;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.lang3.ArrayUtils;
import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class QuartzConfig {
	
    private ApplicationContext applicationContext;
    private DataSource dataSource;
    private CronExpression cronExpression;

    public QuartzConfig(ApplicationContext applicationContext, DataSource dataSource) {
        this.applicationContext = applicationContext;
        this.dataSource = dataSource;
        this.cronExpression = cronExpression;
    }

    @Bean
    public SpringBeanJobFactory springBeanJobFactory() {
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean
    public SchedulerFactoryBean scheduler(Trigger... triggers) {
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();

        Properties properties = new Properties();
        properties.setProperty("org.quartz.scheduler.instanceName", "MyInstanceName");
        properties.setProperty("org.quartz.scheduler.instanceId", "Instance1");

        schedulerFactory.setOverwriteExistingJobs(true);
        schedulerFactory.setAutoStartup(true);
        schedulerFactory.setQuartzProperties(properties);
        schedulerFactory.setDataSource(dataSource);
        schedulerFactory.setJobFactory(springBeanJobFactory());
        schedulerFactory.setWaitForJobsToCompleteOnShutdown(true);

        if (ArrayUtils.isNotEmpty(triggers)) {
            schedulerFactory.setTriggers(triggers);
        }

        return schedulerFactory;
    }

    static SimpleTriggerFactoryBean createTrigger(JobDetail jobDetail, long pollFrequencyMs, String triggerName) {
        
        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
        factoryBean.setJobDetail(jobDetail);
        factoryBean.setStartDelay(0L);
        factoryBean.setRepeatInterval(pollFrequencyMs);
        factoryBean.setName(triggerName);
        factoryBean.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
        factoryBean.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_REMAINING_COUNT);

        return factoryBean;
    }

    public static CronTriggerFactoryBean createCronTrigger(JobDetail jobDetail, String cronExpression, String triggerName) {
        
        // To fix a known issue with time-based cron jobs
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(jobDetail);
        factoryBean.setCronExpression(cronExpression);
        factoryBean.setStartTime(calendar.getTime());
        factoryBean.setStartDelay(0L);
        factoryBean.setName(triggerName);
        factoryBean.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING);

        return factoryBean;
    }

    public static JobDetailFactoryBean createJobDetail(Class jobClass, String jobName) {
        
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setName(jobName);
        factoryBean.setJobClass(jobClass);
        factoryBean.setDurability(true);

        return factoryBean;
    }
}
