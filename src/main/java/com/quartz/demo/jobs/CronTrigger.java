package com.quartz.demo.jobs;

import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import com.quartz.demo.component.QuartzConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class CronTrigger {

	private static final String CRON_EVERY_FIVE_MINUTES = "0 0/1 * * * ? *";

    @Bean(name = "ReenviaPix")
    public JobDetailFactoryBean jobReenviaPix() {
        return QuartzConfig.createJobDetail(ReenviaPix.class, "Member Statistics Job");
    }
    
    @Bean(name = "ReenviaPixTrigger")
    public CronTriggerFactoryBean triggerReenviaPix (@Qualifier("ReenviaPix") JobDetail jobDetail) {
    	return QuartzConfig.createCronTrigger(jobDetail, CRON_EVERY_FIVE_MINUTES, "Class Statistics Trigger");
    }

	
}
