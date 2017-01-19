package com.ejt.usuario;

public class UsuarioNaoEncontradoException extends Exception {
	
	public UsuarioNaoEncontradoException(){
		super("Nenhum Usuario Foi Encontrado !");
	}
}
