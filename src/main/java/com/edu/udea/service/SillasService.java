package com.edu.udea.service;

import java.util.ArrayList;
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

import com.edu.udea.bl.SillasBL;
import com.edu.udea.dto.Silla;
import com.edu.udea.excepcion.Excepcion;

@Component
@Path("/sillas")
public class SillasService {

	@Autowired
	SillasBL sillasBl;
	
	@GET
	@Path("/listar")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response listarSillas(
			@QueryParam("vuelo") Integer vuelo) throws Excepcion{
		
		List<Silla> sillas = new ArrayList<Silla>();
		sillas = sillasBl.listarSillas(vuelo);
		
		return Response.ok().entity(sillas).build();
	}
	
	@GET
	@Path("/contar")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response contarSillas(
			@QueryParam("vuelo") Integer vuelo) throws Excepcion{
		
		Integer sillas = 0;
		sillas = sillasBl.sillasVacias(vuelo);
		
		return Response.ok().entity(sillas).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/editar")
	public Response editarSilla(Silla silla) throws Excepcion {
		
		Boolean valido = sillasBl.agregarPasaje(
				silla.getId_silla(), silla.getPasaje().getId_pasaje());
		
		if (valido) {
			return Response
					.status(Response.Status.ACCEPTED)
					.build();
		}
		else {
			return Response
					.status(Response.Status.NOT_MODIFIED)
					.build();
		}	
		
	}
	
	
}
