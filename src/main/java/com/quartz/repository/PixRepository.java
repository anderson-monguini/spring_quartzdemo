package com.quartz.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.quartz.model.PixModel;

public interface PixRepository extends Repository <PixModel, Integer>{
		
		void save(PixModel pixmodel);
		 
		public List<PixModel> findAll();
		
	    Optional < PixModel > findById(Integer idpix);
	    
	    public List <PixModel> findBystatuspix(String statuspix);
	        
}
