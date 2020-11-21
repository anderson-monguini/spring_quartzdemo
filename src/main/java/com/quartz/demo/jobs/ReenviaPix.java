package com.quartz.demo.jobs;

import java.util.Date;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@DisallowConcurrentExecution
public class ReenviaPix implements Job {

	@Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
          
         System.out.println("Now is" + new Date());

    }
}