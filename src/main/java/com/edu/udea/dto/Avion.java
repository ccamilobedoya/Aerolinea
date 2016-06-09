package com.edu.udea.dto;

import java.io.Serializable;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class Avion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2592096474362329391L;

	private Integer id_avion;
	private String nombre;
	private Integer capacidad;
	private Integer filas;
	private Integer columnas;
	
	public Integer getId_avion() {
		return id_avion;
	}
	public void setId_avion(Integer id_avion) {
		this.id_avion = id_avion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}
	public Integer getFilas() {
		return filas;
	}
	public void setFilas(Integer filas) {
		this.filas = filas;
	}
	public Integer getColumnas() {
		return columnas;
	}
	public void setColumnas(Integer columnas) {
		this.columnas = columnas;
	}
	
	
}
