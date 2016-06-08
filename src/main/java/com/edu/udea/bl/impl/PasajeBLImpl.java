package com.edu.udea.bl.impl;

import java.util.ArrayList;
import java.util.List;

import com.edu.udea.bl.PasajeBL;
import com.edu.udea.dao.ClienteDAO;
import com.edu.udea.dao.ItinerarioDAO;
import com.edu.udea.dao.PasajeDAO;
import com.edu.udea.dao.SocioDAO;
import com.edu.udea.dao.TipoDocumentoDAO;
import com.edu.udea.dao.VueloDAO;
import com.edu.udea.dto.Cliente;
import com.edu.udea.dto.Itinerario;
import com.edu.udea.dto.Pasaje;
import com.edu.udea.dto.Socio;
import com.edu.udea.dto.Vuelo;
import com.edu.udea.excepcion.Excepcion;

public class PasajeBLImpl implements PasajeBL{
	
	PasajeDAO pasajeDao;
	ClienteDAO clienteDao;
	TipoDocumentoDAO tipodocumentoDao;
	ItinerarioDAO itinerarioDao;
	SocioDAO socioDao;
	VueloDAO vueloDao;

	@Override
	public List<Pasaje> consultar(String documento, String tipoDocumento) throws Excepcion {
		List<Pasaje> pasajes = new ArrayList<Pasaje>();
		Cliente cliente = clienteDao.consultar(documento, tipodocumentoDao.consultar(tipoDocumento));
		pasajes = pasajeDao.consultar(cliente);
		return pasajes;
	}
	
	@Override
	public Pasaje consultar(String documento, String tipoDocumento, Integer itinerario) throws Excepcion {
		List<Pasaje> pasajes = new ArrayList<Pasaje>();
		Cliente cliente = clienteDao.consultar(documento, tipodocumentoDao.consultar(tipoDocumento));
		pasajes = pasajeDao.consultar(cliente);
		/*
		for (Pasaje p : pasajes){
			if (p.getItinerario().getId_itinerario() == itinerario){
				return p;
			}
		}
		*/
		for (int i = pasajes.size()-1; i >= 0; i--){
			if (pasajes.get(i).getItinerario().getId_itinerario() == itinerario){
				return pasajes.get(i);
			}
		}
		
		return null;
	}	

	@Override
	public Boolean guardar(String documento, String tipoDocumento, Integer itinerario, Boolean pagado)
			throws Excepcion {
		
		Boolean guardado = false;
		Cliente cliente = clienteDao.consultar(documento, tipodocumentoDao.consultar(tipoDocumento));
		Itinerario iti = itinerarioDao.consultar(itinerario);
		Pasaje pasaje = new Pasaje();
		pasaje.setCliente(cliente);
		pasaje.setItinerario(iti);
		pasaje.setPagado(pagado);
		
		Socio socio = null;
		socio = socioDao.consultar(cliente);
		if (pagado) {			
			guardado = pasajeDao.guardar(pasaje);
			if (socio != null){
				Vuelo vuelo = vueloDao.consultar(iti).get(0);
				socio.setMillas(socio.getMillas() + vuelo.getMillas());
				socioDao.actualizar(socio);
			}
		}
		else { //Si paga con millas no gana millas
			if (socio != null && socio.getMillas() > iti.getPreciocompleto()){
				socio.setMillas(socio.getMillas() - iti.getPreciocompleto());
				socioDao.actualizar(socio);
				guardado = pasajeDao.guardar(pasaje);
			}
		}
				
		return guardado;
	}

	public void setPasajeDao(PasajeDAO pasajeDao) {
		this.pasajeDao = pasajeDao;
	}

	public void setClienteDao(ClienteDAO clienteDao) {
		this.clienteDao = clienteDao;
	}

	public void setTipodocumentoDao(TipoDocumentoDAO tipodocumentoDao) {
		this.tipodocumentoDao = tipodocumentoDao;
	}

	public void setItinerarioDao(ItinerarioDAO itinerarioDao) {
		this.itinerarioDao = itinerarioDao;
	}

	public void setSocioDao(SocioDAO socioDao) {
		this.socioDao = socioDao;
	}

	public void setVueloDao(VueloDAO vueloDao) {
		this.vueloDao = vueloDao;
	}
	
	

}
