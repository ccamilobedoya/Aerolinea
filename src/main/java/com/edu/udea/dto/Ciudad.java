package com.edu.udea.dto;

import java.io.Serializable;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class Ciudad implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8645686898855941864L;

	
	private Integer id_ciudad;
	private Pais pais;
	private String nombre;
	
	public Integer getId_ciudad() {
		return id_ciudad;
	}
	public void setId_ciudad(Integer id_ciudad) {
		this.id_ciudad = id_ciudad;
	}
	public Pais getPais() {
		return pais;
	}
	public void setPais(Pais pais) {
		this.pais = pais;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
