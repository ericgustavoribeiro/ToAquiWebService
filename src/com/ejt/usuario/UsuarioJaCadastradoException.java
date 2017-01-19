package com.ejt.usuario;

public class UsuarioJaCadastradoException extends Exception{

	public UsuarioJaCadastradoException(){
		super("Algum Usuario Já Foi Cadastrado Com Esse E-mail !");
	}
}
