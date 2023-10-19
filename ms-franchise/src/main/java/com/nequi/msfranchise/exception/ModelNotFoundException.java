package com.nequi.msfranchise.exception;

/**
 * Clase excepción personalizada ModelNotFound
 */
public class ModelNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public ModelNotFoundException(String msj) {
		super(msj);
	}
}
