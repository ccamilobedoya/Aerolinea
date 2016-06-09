package com.edu.udea.dto;

import java.io.Serializable;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class Silla implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4572405213891211046L;

	private Integer id_silla;
	private Vuelo vuelo;
	private Pasaje pasaje;
	private TipoClase tipoclase;
	private Integer fila;
	private Integer columna;
	
	public Integer getId_silla() {
		return id_silla;
	}
	public void setId_silla(Integer id_silla) {
		this.id_silla = id_silla;
	}
	public Vuelo getVuelo() {
		return vuelo;
	}
	public void setVuelo(Vuelo vuelo) {
		this.vuelo = vuelo;
	}
	public Pasaje getPasaje() {
		return pasaje;
	}
	public void setPasaje(Pasaje pasaje) {
		this.pasaje = pasaje;
	}
	public TipoClase getTipoclase() {
		return tipoclase;
	}
	public void setTipoclase(TipoClase tipoclase) {
		this.tipoclase = tipoclase;
	}
	public Integer getFila() {
		return fila;
	}
	public void setFila(Integer fila) {
		this.fila = fila;
	}
	public Integer getColumna() {
		return columna;
	}
	public void setColumna(Integer columna) {
		this.columna = columna;
	}
	
	
	
}
