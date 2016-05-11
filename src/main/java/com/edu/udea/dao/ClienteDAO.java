package com.edu.udea.dao;

import java.util.List;

import com.edu.udea.dto.Cliente;
import com.edu.udea.dto.TipoDocumento;
import com.edu.udea.excepcion.Excepcion;

public interface ClienteDAO {

	public List<Cliente> consultar() throws Excepcion;
	
	public Cliente consultar(Integer id_cliente) throws Excepcion;

	public Cliente consultar(String documento, TipoDocumento tipoDocumento) throws Excepcion;
	
	public Boolean guardar (Cliente cliente) throws Excepcion;

}
