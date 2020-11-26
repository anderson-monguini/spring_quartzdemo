package com.quartz.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

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
	
	@DateTimeFormat (pattern = "yyyy-MM-dd")
	@Column (name="data_gmud")
	private LocalDate data_gmud;

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
	
	public LocalDate getData() {
		return data_gmud;
	}

	public void setData(LocalDate data_gmud) {
		this.data_gmud = data_gmud;
	}
	
}
