package com.edu.udea.dao;

import java.util.List;

import com.edu.udea.dto.Aeropuerto;
import com.edu.udea.excepcion.Excepcion;

public interface AeropuertoDAO {
	
	public List<Aeropuerto> consultar() throws Excepcion;
	
	public Aeropuerto consultar(String IATA) throws Excepcion;
	
	public Aeropuerto consultar(Integer id_aeropuerto) throws Excepcion;

}
