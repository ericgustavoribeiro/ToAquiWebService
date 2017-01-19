package com.ejt.usuario;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IRepositorioUsuario {
	public boolean login(String email, String senha) throws UsuarioNaoEncontradoException, SQLException;
	public void cadastrar(Usuario usuario) throws UsuarioJaCadastradoException, CampoObrigatorioException, SenhaInvalidaException, SQLException;
	public void atualizar(Usuario usuario) throws CampoObrigatorioException, SenhaInvalidaException, UsuarioNaoEncontradoException, SQLException;
	public void remover(String email) throws UsuarioNaoEncontradoException, SQLException;
	public void removerId(int id_user) throws UsuarioNaoEncontradoException, SQLException;
	public Usuario procurarNome(String nome) throws UsuarioNaoEncontradoException, SQLException;
	public Usuario procurarId(int id_user) throws UsuarioNaoEncontradoException, SQLException;
	public Usuario procurarEmail(String email) throws UsuarioNaoEncontradoException, SQLException;
	public boolean existe(String email) throws UsuarioNaoEncontradoException, SQLException;
	public ArrayList<Usuario> listar() throws SQLException;
	public ArrayList<Usuario> listar(String complemento) throws SQLException;
}
