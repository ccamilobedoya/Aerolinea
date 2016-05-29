package com.edu.udea.dao;

import java.util.List;

import com.edu.udea.dto.Avion;
import com.edu.udea.excepcion.Excepcion;

public interface AvionDAO {

	public List<Avion> consultar() throws Excepcion;
	public Avion consultar(Integer id_avion) throws Excepcion;
	
}
