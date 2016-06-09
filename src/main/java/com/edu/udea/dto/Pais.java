package com.edu.udea.dto;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Pais implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3740647856475508911L;

	private Integer id_pais;
	private String nombre;
	
	public Integer getId_pais() {
		return id_pais;
	}
	public void setId_pais(Integer id_pais) {
		this.id_pais = id_pais;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
}
