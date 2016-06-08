package com.edu.udea.bl.impl;

import com.edu.udea.bl.ClienteBL;
import com.edu.udea.dao.ClienteDAO;
import com.edu.udea.dao.TipoDocumentoDAO;
import com.edu.udea.dto.Cliente;
import com.edu.udea.excepcion.Excepcion;

public class ClienteBLImpl implements ClienteBL{
	
	ClienteDAO clienteDao;
	TipoDocumentoDAO tipodocumentoDao;

	@Override
	public Cliente consultar(Integer id_cliente) throws Excepcion {
		Cliente cliente = new Cliente();
		if (id_cliente == null){
			return cliente;
		}
		cliente = clienteDao.consultar(id_cliente);
		return cliente;
	}

	@Override
	public Cliente consultar(String documento, String tipoDocumento) throws Excepcion {
		Cliente cliente = new Cliente();
		if (documento.isEmpty() || tipoDocumento.isEmpty()){
			return cliente;
		}
		cliente = clienteDao.consultar(documento, tipodocumentoDao.consultar(tipoDocumento));
		return cliente;
	}

	@Override
	public Boolean guardar(String documento, String tipoDocumento, String nombre, String correo) throws Excepcion {
		
		Cliente cliente = new Cliente();
		
		if (documento.isEmpty() || tipoDocumento.isEmpty() || nombre.isEmpty() || correo.isEmpty()){
			return false;
		}
		
		cliente.setDocumento(documento);
		cliente.setTipoDocumento(tipodocumentoDao.consultar(tipoDocumento));
		cliente.setCorreo(correo);
		cliente.setNombre(nombre);
		
		
		if (clienteDao.consultar(documento, tipodocumentoDao.consultar(tipoDocumento)) != null){
			System.out.println("cliente ya existe");
			return true;
		}
		
		return clienteDao.guardar(cliente);
	}

	public void setClienteDao(ClienteDAO clienteDao) {
		this.clienteDao = clienteDao;
	}

	public void setTipodocumentoDao(TipoDocumentoDAO tipodocumentoDao) {
		this.tipodocumentoDao = tipodocumentoDao;
	}
	
	

}
