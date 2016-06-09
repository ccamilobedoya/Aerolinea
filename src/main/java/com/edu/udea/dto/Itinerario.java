package com.edu.udea.dto;

import java.io.Serializable;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class Itinerario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 258983923648062461L;

	private Integer id_itinerario;
	private Double preciocompleto;
	
	public Integer getId_itinerario() {
		return id_itinerario;
	}
	public void setId_itinerario(Integer id_itinerario) {
		this.id_itinerario = id_itinerario;
	}
	public Double getPreciocompleto() {
		return preciocompleto;
	}
	public void setPreciocompleto(Double preciocompleto) {
		this.preciocompleto = preciocompleto;
	}
	
	
}
