package com.edu.udea.bl;

import java.util.List;

import com.edu.udea.dto.Silla;
import com.edu.udea.excepcion.Excepcion;

public interface SillasBL {

	public List<Silla> listarSillas(Integer idVuelo) throws Excepcion;
	public Integer sillasVacias(Integer idVuelo) throws Excepcion;
	public Boolean agregarPasaje(Integer idSilla, Integer idPasaje) throws Excepcion;
	
}
