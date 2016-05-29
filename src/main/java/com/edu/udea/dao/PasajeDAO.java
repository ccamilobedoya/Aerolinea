package com.edu.udea.dao;

import java.util.List;

import com.edu.udea.dto.Cliente;
import com.edu.udea.dto.Pasaje;
import com.edu.udea.excepcion.Excepcion;

public interface PasajeDAO {

	public List<Pasaje> consultar() throws Excepcion;
	public Pasaje consultar(Integer id_pasaje) throws Excepcion;
	public List<Pasaje> consultar(Cliente cliente) throws Excepcion;
	public Boolean guardar(Pasaje pasaje) throws Excepcion;
}
