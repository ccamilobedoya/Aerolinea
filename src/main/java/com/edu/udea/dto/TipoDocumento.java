package com.edu.udea.dto;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TipoDocumento implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6215335231371789731L;

	private Integer id_tipodocumento;
	private Pais pais;
	private String nombre;
	
	public Integer getId_tipodocumento() {
		return id_tipodocumento;
	}
	public void setId_tipodocumento(Integer id_tipodocumento) {
		this.id_tipodocumento = id_tipodocumento;
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
