package com.edu.udea.bl.impl;


import com.edu.udea.bl.SocioBL;
import com.edu.udea.dao.ClienteDAO;
import com.edu.udea.dao.SocioDAO;
import com.edu.udea.dao.TipoDocumentoDAO;
import com.edu.udea.dto.Cliente;
import com.edu.udea.dto.Socio;
import com.edu.udea.excepcion.Excepcion;

public class SocioBLImpl implements SocioBL {

	SocioDAO socioDao;
	ClienteDAO clienteDao;
	TipoDocumentoDAO tipodocumentoDao;
	
	public Boolean crearSocio(String documento, String tipodocumento, String usuario, String contrasena)
			throws Excepcion {

		// Suponiendo que no hay campos vacios ni con caracteres raros (AQUI VAN VALIDACIONES)
		
		if (documento.equals(null) || tipodocumento.equals(null) || usuario.equals(null) || contrasena.equals(null)){
			throw new Excepcion("No hay datos suficientes para crear un nuevo socio", null);
		}
		
		Boolean hecho = false;
		
		Socio socio = new Socio();
		socio.setUsuario(usuario);
		socio.setContrasena(contrasena);
		socio.setMillas(0.0);
		
		Cliente cliente = null;
		cliente = clienteDao.consultar(documento, tipodocumentoDao.consultar(tipodocumento));
		if (cliente.equals(null))
			throw new Excepcion("No existe un cliente con esa identificacion que pueda ser socio", null);
		socio.setCliente(cliente);
		
		hecho = socioDao.guardar(socio);	
		return hecho;
	}

	public Boolean validarSocio(String usuario, String contrasena) throws Excepcion {
		Boolean valido = false;
		Socio socio = null;
		
		socio = socioDao.consultar(usuario);
		if (socio.getContrasena().equals(contrasena))
			valido = true;
		
		return valido;
	}

	public Boolean editarContrasena(String usuario, String contrasena, String nuevaContrasena) throws Excepcion {
		Boolean hecho = false;
		Socio socio = null;
		
		if (validarSocio(usuario, contrasena)) {
			socio = socioDao.consultar(usuario);
			socio.setContrasena(nuevaContrasena);
			hecho = socioDao.actualizar(socio);
		}
		
		return hecho;
	}

	public Boolean editarMillas(String documento, String tipodocumento, Double millas) throws Excepcion {
		Boolean hecho = false;
		Socio socio = null;
		Cliente cliente = null;
		
		cliente = clienteDao.consultar(documento, tipodocumentoDao.consultar(tipodocumento));
		socio = socioDao.consultar(cliente);
		socio.setMillas(socio.getMillas() + millas);
		hecho = socioDao.actualizar(socio);
		
		return hecho;
	}

	// Inyeccion mediante setters (Spring)
	public void setSocioDao(SocioDAO socioDao) {
		this.socioDao = socioDao;
	}
	public void setClienteDao(ClienteDAO clienteDao) {
		this.clienteDao = clienteDao;
	}
	public void setTipodocumentoDao(TipoDocumentoDAO tipodocumentoDao) {
		this.tipodocumentoDao = tipodocumentoDao;
	}

}




