package com.edu.udea.dao;

import java.util.List;

import com.edu.udea.dto.Pasaje;
import com.edu.udea.dto.Silla;
import com.edu.udea.dto.Vuelo;
import com.edu.udea.excepcion.Excepcion;

public interface SillaDAO {

	public List<Silla> consultar(Vuelo vuelo) throws Excepcion;
	public Silla consultar(Pasaje pasaje) throws Excepcion;
	public Silla consultar(Integer id_silla) throws Excepcion;
	public Boolean actualizar(Silla silla) throws Excepcion;
}
