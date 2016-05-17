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

		Boolean hecho = false;
		
		if (documento.isEmpty() || tipodocumento.isEmpty() || usuario.isEmpty() || contrasena.isEmpty()){
			//throw new Excepcion("No hay datos suficientes para crear un nuevo socio", null);
			System.out.println("retorna 1");
			return hecho;
		}
		
		for (Socio sociotmp : socioDao.consultar()){
			if (sociotmp.getUsuario() == usuario){
				//throw new Excepcion("Ya existe un socio", null);
				System.out.println("retorna 2");
				return hecho;
			}
		}
		
		Socio socio = new Socio();
		socio.setUsuario(usuario);
		socio.setContrasena(contrasena);
		socio.setMillas(0.0);
		
		for (Cliente cliente : clienteDao.consultar()){
			if (cliente.getDocumento().equals(documento) && cliente.getTipoDocumento().getNombre().equals(tipodocumento)){
				System.out.println("------entro al if");
				socio.setCliente(cliente);
				hecho = socioDao.guardar(socio);
				return hecho;
			}
		}		
		System.out.println("retorna 3");
		return hecho;	
	}

	public Boolean validarSocio(String usuario, String contrasena) throws Excepcion {
		Boolean valido = false;
		
		for (Socio socio : socioDao.consultar()){
			System.out.println("-- Compara: " + socio.getUsuario() + " " + usuario);
			if (socio.getUsuario().equals(usuario) && socio.getContrasena().equals(contrasena) ){
				System.out.println("---- Entro al IF");
				valido = true;
				return valido;
			}
		}
		
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




