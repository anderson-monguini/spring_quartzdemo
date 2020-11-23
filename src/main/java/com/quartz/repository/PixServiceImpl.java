package com.quartz.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quartz.model.PixModel;

@Service
public class PixServiceImpl implements PixRepository  {

	@Autowired
	private PixRepository pixRepository;

	@Override
	public Optional < PixModel > findById(Integer idpix) {
	return pixRepository.findById(idpix);
	}

	@Override
	public void save(PixModel pixmodel) {
		pixRepository.save(pixmodel);
	}

	@Override
	public List <PixModel> findAll() {
	return pixRepository.findAll();
	}

	@Override
	public List<PixModel> findBystatuspix(String statuspix) {
	return pixRepository.findBystatuspix(statuspix);
	}


}
