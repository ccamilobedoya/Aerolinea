package com.edu.udea.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.edu.udea.dao.AeropuertoDAO;
import com.edu.udea.dto.Aeropuerto;
import com.edu.udea.excepcion.Excepcion;

@Component
@Path("/aeropuertos")
public class AeropuertoService {
	
	@Autowired
	AeropuertoDAO aeropuertoDao;
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response listarAeropuerto() throws Excepcion{
		List<Aeropuerto> aeropuertos = new ArrayList<Aeropuerto>();
		aeropuertos = aeropuertoDao.consultar();
		
		return Response.ok().entity(aeropuertos).build();
	}
}
