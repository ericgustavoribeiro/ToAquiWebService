package com.ejt.usuario;

public class UsuarioJaCadastradoException extends Exception{

	public UsuarioJaCadastradoException(){
		super("Algum Usuario J� Foi Cadastrado Com Esse E-mail !");
	}
}
