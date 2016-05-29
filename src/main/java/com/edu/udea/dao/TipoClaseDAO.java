package com.edu.udea.dao;


import com.edu.udea.dto.TipoClase;
import com.edu.udea.excepcion.Excepcion;

public interface TipoClaseDAO {

	public TipoClase consultar(Integer id_tipoclase) throws Excepcion;
}
