package com.edu.udea.dao;

import java.util.List;

import com.edu.udea.dto.TipoDocumento;
import com.edu.udea.excepcion.Excepcion;

public interface TipoDocumentoDAO {
	
	public List<TipoDocumento> consultar() throws Excepcion;
	
	public TipoDocumento consultar(Integer id_tipodocumento) throws Excepcion;
	
	public TipoDocumento consultar(String nombre) throws Excepcion;
	
}
