package com.quartz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class PixModel {

	public PixModel() {
		
	}
		
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column (name="idpix")
	private Integer idpix;
	
	@NotNull
	@Column (name="statuspix",length=5)
	private String statuspix;

	public PixModel (String statuspix ) {
		this.statuspix = statuspix;
	}
	
	/* Geters e Seters */
	
	public Integer getIdpix() {
		return idpix;
	}

	public void setIdpix(Integer idpix) {
		this.idpix = idpix;
	}

	public String getStatuspix() {
		return statuspix;
	}

	public void setStatuspix(String statuspix) {
		this.statuspix = statuspix;
	}
	
}
