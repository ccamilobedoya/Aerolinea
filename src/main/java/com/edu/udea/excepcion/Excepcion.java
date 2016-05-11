package com.edu.udea.excepcion;

public class Excepcion extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6575983626514495110L;
	
	public Excepcion(String mensaje, Throwable causa){
		super(mensaje, causa);
	}
	
	public Excepcion(Throwable causa){
		super(causa);
	}

}
