package com.edu.udea.dto;

import java.io.Serializable;

public class TipoClase implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5429158849723709024L;

	private Integer id_tipoclase;
	private String nombre;
	private Double multiplicadorprecio;
	
	public Integer getId_tipoclase() {
		return id_tipoclase;
	}
	public void setId_tipoclase(Integer id_tipoclase) {
		this.id_tipoclase = id_tipoclase;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Double getMultiplicadorprecio() {
		return multiplicadorprecio;
	}
	public void setMultiplicadorprecio(Double multiplicadorprecio) {
		this.multiplicadorprecio = multiplicadorprecio;
	}
	
}
