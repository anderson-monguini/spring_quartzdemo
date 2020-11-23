package com.quartz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.quartz.model.PixModel;
import com.quartz.repository.PixRepository;

@RestController
public class PixController {

	@Autowired
	private PixRepository pixRepository;
	
	@RequestMapping(value = "/cadastropix", method =  RequestMethod.POST)
	public @ResponseBody PixModel cadastraPix(@RequestBody PixModel pix) {
		this.pixRepository.save(pix);
		return pix;
	}
}