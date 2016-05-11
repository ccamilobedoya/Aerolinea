package com.edu.udea.dao;

import java.util.List;

import com.edu.udea.dto.Pais;
import com.edu.udea.excepcion.Excepcion;

public interface PaisDAO{

	public List<Pais> consultar() throws Excepcion;
	
	public Pais consultar(Integer id_pais) throws Excepcion;
	
	public Pais consultar(String nombre) throws Excepcion;
	
	public Boolean guardar(Pais pais) throws Excepcion;
	
	public Boolean actualizar(Pais pais) throws Excepcion;
	
	public Boolean borrar(Pais pais) throws Excepcion;
	
}
