package com.quartz.jobs;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.quartz.model.PixModel;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.SchedulerLock;

@Slf4j
@Component
public class ReenviaPix {
	
	@PersistenceContext private EntityManager entityManager;
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	@Scheduled(cron = "${CronExpression}")
    @SchedulerLock(name = "TaskScheduler_scheduledTask", 
    lockAtLeastForString = "PT2M", lockAtMostForString = "PT10M")
    public void scheduledTask() {
    
	System.out.println("Começando o reprocessamento: " + new Date());
	  
	EntityManager em = entityManager; List<PixModel> list = new
	ArrayList<PixModel>(); TypedQuery<PixModel> 
	query =	em.createQuery("from PixModel where statuspix='erro' AND data_gmud > DATE_ADD(NOW(), INTERVAL -2 DAY", PixModel.class); 
	list= query.getResultList();
	
	//"from PixModel where statuspix='erro' AND data_gmud BETWEEN '2020-11-23' AND '2020-11-23'"
			
	for (int i = 0; i < list.size(); i++) {
		  
	  try {
		  
		  Integer iIdpix = list.get(i).getIdpix();
		  String iStatuspix = list.get(i).getStatuspix();
		  LocalDate iData_pix = list.get(i).getData();
		  
		  String jsonString = new JSONObject()
                  .put("idpix", iIdpix)
                  .put("statuspix", "certo") //.put("statuspix", iStatuspix)
                  .put("data", iData_pix)
                  .toString();

		  String payload = jsonString; StringEntity entity = new
		  StringEntity(payload,org.apache.http.entity.ContentType.APPLICATION_JSON);
		  HttpClient httpClient = HttpClientBuilder.create().build(); 
		  HttpPatch request = new HttpPatch("http://localhost:8080/cadastropix"); request.setEntity(entity);
		  HttpResponse response = httpClient.execute(request);
			  
		  System.out.println(response.getStatusLine().getStatusCode());

		  }catch (Exception e) {
			  e.printStackTrace(); 
			  System.out.println("Vixe mano, deu erro !");
	  }
	}
	System.out.println("LISTA VAZIA");
  }
}