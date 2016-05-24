package com.edu.udea.dto;

import java.io.Serializable;
import java.util.Date;

public class Vuelo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3139420476719394689L;

	private Integer id_vuelo;
	private Avion avion;
	private Itinerario itinerario;
	private Double precio;
	private Aeropuerto desde;
	private Aeropuerto hasta;
	private Double millas;
	private Date salida;
	private Date llegada;
	
	public Date getSalida() {
		return salida;
	}
	public void setSalida(Date salida) {
		this.salida = salida;
	}
	public Date getLlegada() {
		return llegada;
	}
	public void setLlegada(Date llegada) {
		this.llegada = llegada;
	}
	
	public Integer getId_vuelo() {
		return id_vuelo;
	}
	public void setId_vuelo(Integer id_vuelo) {
		this.id_vuelo = id_vuelo;
	}
	public Avion getAvion() {
		return avion;
	}
	public void setAvion(Avion avion) {
		this.avion = avion;
	}
	public Itinerario getItinerario() {
		return itinerario;
	}
	public void setItinerario(Itinerario itinerario) {
		this.itinerario = itinerario;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Aeropuerto getDesde() {
		return desde;
	}
	public void setDesde(Aeropuerto desde) {
		this.desde = desde;
	}
	public Aeropuerto getHasta() {
		return hasta;
	}
	public void setHasta(Aeropuerto hasta) {
		this.hasta = hasta;
	}
	public Double getMillas() {
		return millas;
	}
	public void setMillas(Double millas) {
		this.millas = millas;
	}
	
	
}

