package com.nequi.msauth.exception;

/**
 * Clase excepción personalizada ModelNotFound
 * @author @cperez@Progracol.com
 */
public class ModelNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public ModelNotFoundException(String msj) {
		super(msj);
	}
}
