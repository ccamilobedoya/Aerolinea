package com.edu.udea.dao;

import java.util.List;

import com.edu.udea.dto.Itinerario;
import com.edu.udea.excepcion.Excepcion;

public interface ItinerarioDAO {

	public List<Itinerario> consultar() throws Excepcion;
	public Itinerario consultar(Integer id_itinerario) throws Excepcion;
}
