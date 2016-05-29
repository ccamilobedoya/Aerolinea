package com.edu.udea.dao;

import java.util.Date;
import java.util.List;

import com.edu.udea.dto.Aeropuerto;
import com.edu.udea.dto.Itinerario;
import com.edu.udea.dto.Vuelo;
import com.edu.udea.excepcion.Excepcion;

public interface VueloDAO {

	public List<Vuelo> consultar(Itinerario itinerario) throws Excepcion;
	public List<Vuelo> consultar(Aeropuerto desde, Aeropuerto hasta, Date salida) throws Excepcion;
	public Vuelo consultar(Integer id_vuelo) throws Excepcion;
}
