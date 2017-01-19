package com.ejt.util;

public class RepositorioException extends Exception {
	private Exception exception;
	
	public RepositorioException(Exception exception){
		super("Excecão encapsulada");
		this.exception = exception;
	}

    public String getMessage() {
        return exception.getMessage();
    }

    public void printStackTrace() {
        exception.printStackTrace();
    }
}
