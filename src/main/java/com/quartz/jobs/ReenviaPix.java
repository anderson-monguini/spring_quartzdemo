package com.quartz.jobs;

import java.util.Date;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.quartz.repository.PixServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@DisallowConcurrentExecution
public class ReenviaPix implements Job {
	
	@Autowired
	private PixServiceImpl pixServiceImpl;
	
	@Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		
		try {

		System.out.println("Now is: " + new Date());
        
		//pixServiceImpl.findById(72);
		
		pixServiceImpl.findBystatuspix("erro");
		
		System.out.println("Rodou sem merda");
		
		} catch (Exception e) {
			
			System.out.println("ERRO DE MERDA");
		}
    }
}