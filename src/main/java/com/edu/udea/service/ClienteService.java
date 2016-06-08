package com.edu.udea.service;

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

import com.edu.udea.bl.ClienteBL;
import com.edu.udea.dto.Cliente;
import com.edu.udea.excepcion.Excepcion;

@Component
@Path("/cliente")
public class ClienteService {
	
	@Autowired
	ClienteBL clienteBl;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/guardar")
	public Response guardarSocio(Cliente cliente) throws Excepcion {
		
		Boolean guardado = clienteBl.guardar(
				cliente.getDocumento(),
				cliente.getTipoDocumento().getNombre(),
				cliente.getNombre(),
				cliente.getCorreo());
		
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
			@QueryParam("id") Integer id) throws Excepcion{
		
		Cliente cliente = clienteBl.consultar(id);
		
		return Response.ok().entity(cliente).build();
	}
	
	@GET
	@Path("/consultarpordatos")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response consultar(
			@QueryParam("documento") String documento,
			@QueryParam("tipo") String tipodocumento) throws Excepcion{
		
		Cliente cliente = clienteBl.consultar(documento, tipodocumento);
		
		return Response.ok().entity(cliente).build();
	}
	
}
