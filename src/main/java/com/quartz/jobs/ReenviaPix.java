package com.quartz.jobs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.hibernate.SQLQuery;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.quartz.model.PixModel;
import com.quartz.repository.PixServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@DisallowConcurrentExecution
public class ReenviaPix implements Job {
	
	@Autowired
	private PixServiceImpl pixServiceImpl;
	
	@PersistenceContext
    private EntityManager entityManager;
	
	
	@Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		//try {
			
			EntityManager em = entityManager;
			List<PixModel> list = new ArrayList<PixModel>();
			TypedQuery<PixModel> query = em.createQuery("from PixModel where statuspix='erro'", PixModel.class);
			list = query.getResultList();
									
			list.forEach(System.out::println);
			
			System.out.println("Now is: " + new Date());
	        
			//pixServiceImpl.findById(72);
			//pixServiceImpl.findBystatuspix("erro");
			
		
	//		System.out.println("Rodou sem merda");
			
	//	} catch (Exception e) {
			
	//		System.out.println("ERRO DE MERDA");
	//	}
    }

}