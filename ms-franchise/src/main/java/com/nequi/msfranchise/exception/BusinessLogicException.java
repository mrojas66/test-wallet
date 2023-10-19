package com.nequi.msfranchise.exception;

/**
 * Clase excepción personalizada BusinessLogic
 */
public class BusinessLogicException  extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public BusinessLogicException(String mensaje) {		
		super(mensaje);
	}
}
