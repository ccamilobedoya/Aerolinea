package com.edu.udea.bl;

import com.edu.udea.excepcion.Excepcion;

public interface SocioBL {

	public Boolean crearSocio(String documento, String tipodocumento, String usuario, String contrasena) throws Excepcion;
	
	public Boolean validarSocio(String usuario, String contrasena) throws Excepcion;
	
	public Boolean editarContrasena(String usuario, String contrasena, String nuevaContrasena) throws Excepcion;
	
	public Boolean editarMillas(String documento, String tipodocumento, Double millas) throws Excepcion;
	
}
