package com.ejt.avaliacao;

import java.sql.SQLException;
import java.util.ArrayList;

public class ControladorAvaliacao {

	private IRepositorioAvaliacao repositorioAvaliacao;
	
	public ControladorAvaliacao() throws Exception{
		this.repositorioAvaliacao = new RepositorioAvaliacaoBDR();
	}
	
	
	public void cadastrar(Avaliacao avaliacao) throws AvaliacaoJaCadastradaException, CampoObrigatorioException, SQLException{
		//if(avaliacao.getNota()); não lembro como compara inteiro pra saber se o valor e nulo
		if (this.repositorioAvaliacao.existe(avaliacao.getId_user(), avaliacao.getId_estabelecimento())) throw new AvaliacaoJaCadastradaException();
		
		this.repositorioAvaliacao.cadastrar(avaliacao);
	}
	
	public void atualizar(Avaliacao avaliacao) throws CampoObrigatorioException, SQLException, AvaliacaoNaoEncontradaException{
		//if(avaliacao.getNota()); não lembro como compara inteiro pra saber se o valor e nulo
		 this.repositorioAvaliacao.atualizar(avaliacao);
		
	}
	
	public void remover(int id_user, int id_estabelecimento) throws CampoObrigatorioException, SQLException, AvaliacaoNaoEncontradaException{
		Avaliacao avaliacao = null;
		avaliacao = this.repositorioAvaliacao.procurar(id_user, id_estabelecimento);
		
		this.repositorioAvaliacao.remover(id_user, id_estabelecimento);
	}
	
	public ArrayList<Avaliacao> listar(int id_estabelecimento) throws SQLException{
		
		return this.repositorioAvaliacao.listar(id_estabelecimento);
	}
	
	public boolean existe(int id_user, int id_estabelecimento) throws CampoObrigatorioException, SQLException{
		
		return this.repositorioAvaliacao.existe(id_user, id_estabelecimento);
		
	}
	
}
