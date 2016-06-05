package com.edu.udea.bl.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.edu.udea.bl.BusquedaVuelos;
import com.edu.udea.dao.AeropuertoDAO;
import com.edu.udea.dao.CiudadDAO;
import com.edu.udea.dao.ItinerarioDAO;
import com.edu.udea.dao.PaisDAO;
import com.edu.udea.dao.VueloDAO;
import com.edu.udea.dto.Vuelo;
import com.edu.udea.excepcion.Excepcion;

public class BusquedaVuelosImpl implements BusquedaVuelos{

	VueloDAO vueloDao;
	AeropuertoDAO aeropuertoDao;
	CiudadDAO ciudadDao;
	PaisDAO paisDao;
	ItinerarioDAO itinerarioDao;
	
	@Override
	public List<Vuelo> listarVuelos(String desdeIata, String hastaIata, Date salida) throws Excepcion {
		List<Vuelo> vuelos = new ArrayList<Vuelo>();
		
		if (desdeIata.isEmpty() || hastaIata.isEmpty() || salida == null) {
			System.out.println("Datos vacios --- listarVuelos");
			return null;
		}
		
		vuelos = vueloDao.consultar(
				aeropuertoDao.consultar(desdeIata),
				aeropuertoDao.consultar(hastaIata),
				salida);
		
		return vuelos;
	}

	@Override
	public List<Vuelo> listarVuelos(Integer id_itinerario) throws Excepcion {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vuelo buscarVuelo(Integer id) throws Excepcion {
		Vuelo vuelo = new Vuelo();
		if (id == null) {
			return vuelo;
		}
		vuelo = vueloDao.consultar(id);
		return vuelo;
	}
	
	public void setVueloDao(VueloDAO vueloDao) {
		this.vueloDao = vueloDao;
	}

	public void setAeropuertoDao(AeropuertoDAO aeropuertoDao) {
		this.aeropuertoDao = aeropuertoDao;
	}

	public void setCiudadDao(CiudadDAO ciudadDao) {
		this.ciudadDao = ciudadDao;
	}

	public void setPaisDao(PaisDAO paisDao) {
		this.paisDao = paisDao;
	}

	public void setItinerarioDao(ItinerarioDAO itinerarioDao) {
		this.itinerarioDao = itinerarioDao;
	}

	
	
	

}
