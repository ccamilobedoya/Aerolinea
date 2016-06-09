package com.edu.udea.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.edu.udea.bl.BusquedaVuelos;
import com.edu.udea.dto.Vuelo;
import com.edu.udea.excepcion.Excepcion;



@Component
@Path("/vuelos")
public class BusquedaVuelosService {

	@Autowired
	BusquedaVuelos busquedavuelos;
	
	@GET
	@Path("/busqueda")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response listarVuelos (
			@QueryParam("desde") String desde,
			@QueryParam("hasta") String hasta,
			@QueryParam("salida") String salida) throws Excepcion {
		
		// Parsing de la fecha con el string
		Date fechaSalida = new Date();
		try {
			// Formato ISO = 2016-05-29T17:11:05Z
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			fechaSalida = sdf.parse(salida);
		} catch (ParseException e) {
			System.out.println("Parsing fecha fallido");
		}
		
		List<Vuelo> vuelos = new ArrayList<Vuelo>();
		vuelos = busquedavuelos.listarVuelos(desde, hasta, fechaSalida);

		//  /vuelos/busqueda?desde=BOG&hasta=OLH&salida=2016-05-29T12:00:00Z
		
		return Response
				.ok()
				.entity(vuelos)
				.build();
	}
	
	@GET
	@Path("/busquedaunica")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response buscarVuelo (
			@QueryParam("id") Integer id) throws Excepcion {
		
		Vuelo vuelo = new Vuelo();
		vuelo = busquedavuelos.buscarVuelo(id);
		
		return Response
				.ok()
				.entity(vuelo)
				.build();
	}
	
	@GET
	@Path("/busquedaitinerario")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response buscarVueloPorItinerario (
			@QueryParam("itinerario") Integer id) throws Excepcion {
		
		List<Vuelo> vuelos = new ArrayList<Vuelo>();
		vuelos = busquedavuelos.listarVuelos(id);
		
		return Response
				.ok()
				.entity(vuelos)
				.build();
	}
	
}
