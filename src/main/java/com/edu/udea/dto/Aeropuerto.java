package com.edu.udea.dto;

import java.io.Serializable;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class Aeropuerto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -370640550460933650L;

	private Integer id_aeropuerto;
	private Ciudad ciudad;
	private String nombre;
	private String iata;
	private String direccion;
	private String telefono;
	private String oficina;
	
	public Integer getId_aeropuerto() {
		return id_aeropuerto;
	}
	public void setId_aeropuerto(Integer id_aeropuerto) {
		this.id_aeropuerto = id_aeropuerto;
	}
	public Ciudad getCiudad() {
		return ciudad;
	}
	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getIata() {
		return iata;
	}
	public void setIata(String iata) {
		this.iata = iata;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getOficina() {
		return oficina;
	}
	public void setOficina(String oficina) {
		this.oficina = oficina;
	}
	
	
}
