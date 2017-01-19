package com.ejt.usuario;

public class SenhaInvalidaException extends Exception{

	public SenhaInvalidaException(){
		super("Tente Novamente, Senha ou Email É Invalido !");
	}

}
