package com.edu.udea.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edu.udea.util.EmailUtility;

@Component
@Path("/correo")
public class CorreoService {
	
	@Autowired
	EmailUtility email;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response enviarCorreo(String[] textos) {
		
		email.enviarCorreo(textos[0], textos[1], textos[2], textos[3]);
	
		return Response.ok().build();
	}
	
}
