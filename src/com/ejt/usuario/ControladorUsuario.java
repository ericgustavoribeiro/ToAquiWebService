package com.ejt.usuario;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ejt.estabelecimento.CampoObrigatorioException;
import com.ejt.estabelecimento.Estabelecimento;
import com.ejt.estabelecimento.EstabelecimentoJaCadastradoException;
import com.ejt.estabelecimento.EstabelecimentoNaoEncontradoException;

public class ControladorUsuario {

	private IRepositorioUsuario repositorioUsuario;
	
	public ControladorUsuario() throws Exception{
		this.repositorioUsuario = new RepositorioUsuarioBDR();
	}
	
	public boolean login(String email, String senha) throws UsuarioNaoEncontradoException, SQLException{
		return this.repositorioUsuario.login(email, senha);		
	}
	
	public void cadastrar(Usuario usuario) throws UsuarioJaCadastradoException, com.ejt.usuario.CampoObrigatorioException, SenhaInvalidaException, SQLException, UsuarioNaoEncontradoException, com.ejt.usuario.CampoObrigatorioException {
		if(usuario.getNome().equals("")) throw new com.ejt.usuario.CampoObrigatorioException();
		if(usuario.getEmail().equals("")) throw new com.ejt.usuario.CampoObrigatorioException();
		if(usuario.getSenha().equals("")) throw new com.ejt.usuario.CampoObrigatorioException();
		if(this.repositorioUsuario.existe(usuario.getEmail())) throw new UsuarioJaCadastradoException();
		
		this.repositorioUsuario.cadastrar(usuario);
	}
	
	public void atualizar(Usuario usuario) throws UsuarioJaCadastradoException, com.ejt.usuario.CampoObrigatorioException, SenhaInvalidaException, SQLException, UsuarioNaoEncontradoException{
		if(usuario.getNome().equals("")) throw new com.ejt.usuario.CampoObrigatorioException();
		if(usuario.getSenha().equals("")) throw new com.ejt.usuario.CampoObrigatorioException();
		
		
		this.repositorioUsuario.atualizar(usuario);
	}
	
	public void remover(String email) throws UsuarioNaoEncontradoException, SQLException{
		this.repositorioUsuario.procurarEmail(email);
		this.repositorioUsuario.remover(email);
		
	}
	
	public void removerId(int id_user) throws UsuarioNaoEncontradoException, SQLException{
		this.repositorioUsuario.procurarId(id_user);
		this.repositorioUsuario.removerId(id_user);;
		
	}
	
	public Usuario procurarNome(String nome) throws UsuarioNaoEncontradoException, SQLException{
		
		return this.repositorioUsuario.procurarNome(nome);
	}
	
	public Usuario procurarEmail(String email) throws UsuarioNaoEncontradoException, SQLException{
		return this.repositorioUsuario.procurarEmail(email);
		
	}
	
	public Usuario procurarId(int id_user) throws UsuarioNaoEncontradoException, SQLException{
		
		return this.repositorioUsuario.procurarId(id_user);
	}
	
	public ArrayList<Usuario> listar() throws SQLException{
		return this.repositorioUsuario.listar();
		
	}

	
	
}
