package com.ejt.adm;

public class SenhaInvalidaException extends Exception{

	public SenhaInvalidaException() {
		super("Senha ou Email Invalidos, Verifique !");
	}

}
