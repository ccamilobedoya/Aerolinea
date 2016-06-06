package com.edu.udea.bl.impl;

import java.util.ArrayList;
import java.util.List;

import com.edu.udea.bl.SillasBL;
import com.edu.udea.dao.PasajeDAO;
import com.edu.udea.dao.SillaDAO;
import com.edu.udea.dao.VueloDAO;
import com.edu.udea.dto.Pasaje;
import com.edu.udea.dto.Silla;
import com.edu.udea.dto.Vuelo;
import com.edu.udea.excepcion.Excepcion;

public class SillasBLImpl implements SillasBL {

	SillaDAO sillaDao;
	PasajeDAO pasajeDao;
	VueloDAO vueloDao;
	
	@Override
	public List<Silla> listarSillas(Integer idVuelo) throws Excepcion {
		List<Silla> sillas = new ArrayList<Silla>();
		if (idVuelo == null){
			return sillas;
		}
		Vuelo vuelo = vueloDao.consultar(idVuelo);
		sillas = sillaDao.consultar(vuelo);
		return sillas;
	}

	@Override
	public Integer sillasVacias(Integer idVuelo) throws Excepcion {
		List<Silla> sillas = new ArrayList<Silla>();
		if (idVuelo == null){
			return 0;
		}
		Vuelo vuelo = vueloDao.consultar(idVuelo);
		sillas = sillaDao.consultar(vuelo);
		
		int total = 0;
		for (int i = 0; i < sillas.size(); i++){
			if (sillas.get(i).getPasaje() == null){
				total++;
			}
		}
		
		return total;
	}

	@Override
	public Boolean agregarPasaje(Integer idSilla, Integer idPasaje) throws Excepcion {
		if (idSilla == null || idPasaje == null){
			return false;
		}		
		Silla silla = sillaDao.consultar(idSilla);
		Pasaje pasaje = pasajeDao.consultar(idPasaje);
		silla.setPasaje(pasaje);
		
		return sillaDao.actualizar(silla);		
	}

	public void setSillaDao(SillaDAO sillaDao) {
		this.sillaDao = sillaDao;
	}

	public void setPasajeDao(PasajeDAO pasajeDao) {
		this.pasajeDao = pasajeDao;
	}
	
	public void setVueloDao(VueloDAO vueloDao) {
		this.vueloDao = vueloDao;
	}

	
	
}
