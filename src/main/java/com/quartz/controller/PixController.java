package com.quartz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.quartz.model.PixModel;
import com.quartz.model.PixTeste;
import com.quartz.repository.PixRepository;
import com.quartz.repository.PixTesteRepository;

@RestController
public class PixController {

	@Autowired
	private PixRepository pixRepository;
	
	@Autowired
	private PixTesteRepository pixTesteRepository;
	
	@RequestMapping(value = "/cadastropix", method =  RequestMethod.POST)
	public @ResponseBody PixModel cadastraPix(@RequestBody PixModel pix) {
		this.pixRepository.save(pix);
		return pix;
	}
	
	@RequestMapping(value = "/pixteste", method =  RequestMethod.POST)
	public @ResponseBody PixTeste cadastraPix(@RequestBody PixTeste pix) {
		this.pixTesteRepository.save(pix);
		return pix;
	}
}