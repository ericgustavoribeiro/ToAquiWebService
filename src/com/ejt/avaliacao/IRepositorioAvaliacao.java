package com.ejt.avaliacao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IRepositorioAvaliacao {

	public void cadastrar(Avaliacao avaliacao) throws AvaliacaoJaCadastradaException, CampoObrigatorioException, SQLException;
	public void atualizar(Avaliacao avaliacao) throws CampoObrigatorioException, SQLException, AvaliacaoNaoEncontradaException;
	public Avaliacao procurar(int id_user, int id_estabelecimento)throws CampoObrigatorioException, SQLException, AvaliacaoNaoEncontradaException;
	public void remover(int id_user, int id_estabelecimento) throws CampoObrigatorioException, SQLException, AvaliacaoNaoEncontradaException;
	public boolean existe(int id_user, int id_estabelecimento) throws CampoObrigatorioException, SQLException;
	public ArrayList<Avaliacao> listar(String complemento) throws SQLException;
	public ArrayList<Avaliacao> listar(int id_estabelecimento) throws SQLException;
	

}
