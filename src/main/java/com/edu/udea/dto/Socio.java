package com.edu.udea.dto;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Socio implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1214616562253356545L;
	
	private Integer id_socio;
	private Cliente cliente;
	private String usuario;
	private String contrasena;
	private Double millas;
	
	public Integer getId_socio() {
		return id_socio;
	}
	public void setId_socio(Integer id_socio) {
		this.id_socio = id_socio;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public Double getMillas() {
		return millas;
	}
	public void setMillas(Double millas) {
		this.millas = millas;
	}
	
}
