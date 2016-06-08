package com.edu.udea.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.edu.udea.bl.PasajeBL;
import com.edu.udea.dto.Pasaje;
import com.edu.udea.excepcion.Excepcion;

@Component
@Path("/pasaje")
public class PasajeService {

	@Autowired
	PasajeBL pasajeBl;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/guardar")
	public Response guardar(Pasaje pasaje) throws Excepcion {
		Boolean guardado = pasajeBl.guardar(
				pasaje.getCliente().getDocumento(),
				pasaje.getCliente().getTipoDocumento().getNombre(),
				pasaje.getItinerario().getId_itinerario(),
				pasaje.getPagado());
		
		if (guardado) {
			return Response
					.status(Response.Status.CREATED)
					.build();
		}
		else {
			return Response
					.status(Response.Status.BAD_REQUEST)
					.build();
		}		
	}
	
	@GET
	@Path("/consultar")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response consultar(
			@QueryParam("documento") String documento,
			@QueryParam("tipo") String tipodocumento) throws Excepcion{
		
		List<Pasaje> pasajes = pasajeBl.consultar(documento, tipodocumento);
		
		return Response.ok().entity(pasajes).build();		
	}
	
	@GET
	@Path("/consultarconitinerario")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response consultar(
			@QueryParam("documento") String documento,
			@QueryParam("tipo") String tipodocumento,
			@QueryParam("itinerario") Integer itinerario) throws Excepcion{
		
		Pasaje pasaje = pasajeBl.consultar(documento, tipodocumento, itinerario);
		
		return Response.ok().entity(pasaje).build();		
	}
	
}
