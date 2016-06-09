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

import com.edu.udea.bl.SocioBL;
import com.edu.udea.dto.Socio;
import com.edu.udea.excepcion.Excepcion;


@Component
@Path("/socio")
public class SocioService {

	@Autowired
	SocioBL socioBl;
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/registrar")
	public Response guardarSocio(Socio socio) throws Excepcion {

		Boolean creado = socioBl.crearSocio(
				socio.getCliente().getDocumento(),
				socio.getCliente().getTipoDocumento().getNombre(),
				socio.getUsuario(),
				socio.getContrasena());
		
		if (creado) {
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
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/editarcontrasena")
	public Response editarContrasena(String[] lista) throws Excepcion {

		Boolean editado = socioBl.editarContrasena(
				lista[0], 
				lista[1], 
				lista[2]);
		
		if (editado) {
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
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/ingresar")
	public Response ingresarSocio(Socio socio) throws Excepcion {
		 
		Boolean valido = socioBl.validarSocio(
				socio.getUsuario(),
				socio.getContrasena());
		
		
		if (valido) {
			return Response
					.status(Response.Status.OK)
					.build();
		}
		else {
			return Response
					.status(Response.Status.BAD_REQUEST)
					.build();
		}		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/consultar")
	public Response consultar (
			@QueryParam("usuario") String usuario) throws Excepcion {
		
		Socio socio = socioBl.consultar(usuario);
		
		return Response.ok().entity(socio).build();		
	}
	
	
}







