package com.ejt.estabelecimento;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IRepositorioEstabelecimento {
	
	public void cadastrar(Estabelecimento estabelecimento) throws EstabelecimentoJaCadastradoException,CampoObrigatorioException, SQLException; 
	public void atualizar(Estabelecimento estabelecimento) throws EstabelecimentoNaoEncontradoException, SQLException;
	public void atualizarDisponibilidade(int id_estabelecimento) throws EstabelecimentoNaoEncontradoException, SQLException;
	public void removerNome(String nome) throws EstabelecimentoNaoEncontradoException, SQLException;
	public void removerId(int id_estabelecimento) throws EstabelecimentoNaoEncontradoException, SQLException;
	public void removerEmail(String email) throws EstabelecimentoNaoEncontradoException, SQLException;
	public Estabelecimento procurarNome(String nome) throws EstabelecimentoNaoEncontradoException, SQLException;
	public Estabelecimento procurarId(int id_estabelecimento) throws EstabelecimentoNaoEncontradoException, SQLException;
	public Estabelecimento procurarLogradouro(String logradouro) throws EstabelecimentoNaoEncontradoException, SQLException;
	public Estabelecimento procurarBairro(String bairro) throws EstabelecimentoNaoEncontradoException, SQLException;
	public Estabelecimento procurarAvaliacao(String Avaliacao) throws EstabelecimentoNaoEncontradoException, SQLException;
	public boolean existe(String nome) throws EstabelecimentoNaoEncontradoException, SQLException;
	public boolean checarDisponivel(String nome) throws EstabelecimentoNaoEncontradoException, SQLException;
	public ArrayList<Estabelecimento> listar() throws SQLException;
	public ArrayList<Estabelecimento> listarTodos() throws SQLException;
	public ArrayList<Estabelecimento> listarPendentes() throws SQLException;	
	public ArrayList<Estabelecimento> listar(String complemento) throws SQLException;
	public ArrayList<Estabelecimento> listarCadastrados(int id_user) throws SQLException;
	public ArrayList<Estabelecimento> listarCadastradosPendentes(int id_user) throws SQLException;
	public ArrayList<Estabelecimento> listarBuscaNome(String nome) throws CampoObrigatorioException, EstabelecimentoNaoEncontradoException, SQLException;
	
	
}

