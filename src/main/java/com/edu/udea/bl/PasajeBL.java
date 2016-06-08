package com.edu.udea.bl;

import java.util.List;

import com.edu.udea.dto.Pasaje;
import com.edu.udea.excepcion.Excepcion;

public interface PasajeBL {

	public List<Pasaje> consultar(String documento, String tipoDocumento) throws Excepcion;
	public Pasaje consultar(String documento, String tipoDocumento, Integer itinerario) throws Excepcion;
	public Boolean guardar(String documento, String tipoDocumento, Integer itinerario, Boolean pagado) throws Excepcion;
	
	
}
