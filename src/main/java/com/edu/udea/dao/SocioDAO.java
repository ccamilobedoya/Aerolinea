package com.edu.udea.dao;

import java.util.List;

import com.edu.udea.dto.Cliente;
import com.edu.udea.dto.Socio;
import com.edu.udea.excepcion.Excepcion;

public interface SocioDAO {

	public List<Socio> consultar() throws Excepcion;
	
	public Socio consultar(Integer id_socio) throws Excepcion;
	
	public Socio consultar(String usuario) throws Excepcion;
	
	public Socio consultar(Cliente cliente) throws Excepcion;
	
	public Boolean guardar(Socio socio) throws Excepcion;
	
	public Boolean actualizar(Socio socio) throws Excepcion;
	
}
