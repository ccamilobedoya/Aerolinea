package com.edu.udea.dao;

import java.util.List;

import com.edu.udea.dto.Ciudad;
import com.edu.udea.excepcion.Excepcion;

public interface CiudadDAO {

	public List<Ciudad> consultar() throws Excepcion;
	public Ciudad consultar(Integer id_ciudad) throws Excepcion;
	
}
