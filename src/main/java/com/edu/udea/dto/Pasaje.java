package com.edu.udea.dto;

import java.io.Serializable;

public class Pasaje implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8844196470565951403L;

	private Integer id_pasaje;
	private Itinerario itinerario;
	private Cliente cliente;
	private Boolean pagado;
	
	public Integer getId_pasaje() {
		return id_pasaje;
	}
	public void setId_pasaje(Integer id_pasaje) {
		this.id_pasaje = id_pasaje;
	}
	public Itinerario getItinerario() {
		return itinerario;
	}
	public void setItinerario(Itinerario itinerario) {
		this.itinerario = itinerario;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Boolean getPagado() {
		return pagado;
	}
	public void setPagado(Boolean pagado) {
		this.pagado = pagado;
	}
	
	
	
}
