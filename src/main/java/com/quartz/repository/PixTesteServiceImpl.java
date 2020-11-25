package com.quartz.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.quartz.model.PixTeste;

public class PixTesteServiceImpl implements PixTesteRepository{
	
	@Autowired
	private PixTesteRepository pixTesteRepository;

	@Override
	public Optional < PixTeste > findById(Integer idpix) {
	return pixTesteRepository.findById(idpix);
	}

	@Override
	public void save(PixTeste pixmodel) {
		pixTesteRepository.save(pixmodel);
	}

	@Override
	public List <PixTeste> findAll() {
	return pixTesteRepository.findAll();
	}

	@Override
	public List<PixTeste> findBystatuspix(String statuspix) {
	return pixTesteRepository.findBystatuspix(statuspix);
	}

}
