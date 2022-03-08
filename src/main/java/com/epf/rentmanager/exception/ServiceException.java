package com.epf.rentmanager.exception;

public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	public ServiceException() {
		super ("Erreur dans le service");	
	}
	public ServiceException(String s) {
		super (s);	
	}
}
