package com.edu.udea.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
	
	/*
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response guardarSocio(String documento, String tipodocumento, String usuario, String contrasena) throws Excepcion {
		
		System.out.println("-- Servicio: Documento: " + documento);
		System.out.println("-- Servicio: Usuariio: " + usuario);

		socioBl.crearSocio(documento, tipodocumento, usuario, contrasena);
		
		return Response.status(Response.Status.CREATED)
				.build();
	}
	*/
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response guardarSocio(Socio socio) throws Excepcion {
		
		System.out.println("-- Servicio: Documento: " + socio.getCliente().getDocumento());
		System.out.println("-- Servicio: Usuario: " + socio.getUsuario());

		socioBl.crearSocio(
				socio.getCliente().getDocumento(),
				socio.getCliente().getTipoDocumento().getNombre(),
				socio.getUsuario(),
				socio.getContrasena());
		
		return Response.status(Response.Status.CREATED)
				.build();
	}
}







