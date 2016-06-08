package com.edu.udea.bl;

import com.edu.udea.dto.Cliente;
import com.edu.udea.excepcion.Excepcion;

public interface ClienteBL {

	public Cliente consultar(Integer id_cliente) throws Excepcion;
	public Cliente consultar(String documento, String tipoDocumento) throws Excepcion;
	public Boolean guardar(String documento, String tipoDocumento, String nombre, String correo) throws Excepcion;
}
