package com.quartz.jobs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.quartz.model.PixModel;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.SchedulerLock;

@Slf4j
@Component
public class ReenviaPix {
	
	@PersistenceContext private EntityManager entityManager;
	
	private PixModel pixModel;
	
	@Scheduled(cron = "${CronExpression}")
    @SchedulerLock(name = "TaskScheduler_scheduledTask", 
    lockAtLeastForString = "PT1M", lockAtMostForString = "PT1M")
    public void scheduledTask() {
    
	System.out.println("Começando o reprocessamento: " + new Date());
	  
	EntityManager em = entityManager; List<PixModel> list = new
	ArrayList<PixModel>(); TypedQuery<PixModel> 
	query =	em.createQuery("from PixModel where statuspix='erro'", PixModel.class); 
	list= query.getResultList();
		
	//list.stream().map(PixModel::getIdpix).forEach(System.out::println);
	
	 String json = new Gson().toJson(list);
		
	  for (int i = 0; i < list.size(); i++) {
	  		  
	  try {
		  
		 
		  System.out.println(json);
		  

	  
	  }catch (Exception e) { e.printStackTrace(); }
	  
	  
	  }
	}
}