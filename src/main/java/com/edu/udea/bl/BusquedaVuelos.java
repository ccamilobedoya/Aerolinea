package com.edu.udea.bl;

import java.util.Date;
import java.util.List;

import com.edu.udea.dto.Vuelo;
import com.edu.udea.excepcion.Excepcion;

public interface BusquedaVuelos {

	public List<Vuelo> listarVuelos(String desdeIata, String hastaIata, Date salida) throws Excepcion;
	public List<Vuelo> listarVuelos(Integer id_itinerario) throws Excepcion;
	public Vuelo buscarVuelo(Integer id) throws Excepcion;
	
}
