package com.nequi.msauth.exception;

/**
 * Clase excepción personalizada BusinessLogic
 * @author cperez@progracol.com
 */
public class BusinessLogicException  extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public BusinessLogicException(String mensaje) {		
		super(mensaje);
	}
}
