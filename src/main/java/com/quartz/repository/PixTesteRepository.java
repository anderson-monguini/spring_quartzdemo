package com.quartz.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.quartz.model.PixTeste;

public interface PixTesteRepository extends Repository <PixTeste, Integer>{
	
	void save(PixTeste pixTeste);
	 
	public List<PixTeste> findAll();
	
    Optional < PixTeste > findById(Integer idpix);
    
    public List <PixTeste> findBystatuspix(String statuspix);
        
}
