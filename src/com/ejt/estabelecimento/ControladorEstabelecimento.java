package com.ejt.estabelecimento;

import java.sql.SQLException;
import java.util.ArrayList;
import java.text.Normalizer;

public class ControladorEstabelecimento {

	private IRepositorioEstabelecimento repositorioEstabelecimento;
	
	
	public ControladorEstabelecimento() throws Exception {
		this.repositorioEstabelecimento = new RepositorioEstabelecimentoBDR();
	}
	
	public void cadastrar(Estabelecimento estabelecimento) throws EstabelecimentoJaCadastradoException,CampoObrigatorioException, SQLException, EstabelecimentoNaoEncontradoException {
		if(estabelecimento.getNome().equals("")) throw new CampoObrigatorioException();
		if(estabelecimento.getEndereco().getEndereco().equals("")) throw new CampoObrigatorioException();
//		if(estabelecimento.getEndereco().getLongitude().equals("")) throw new CampoObrigatorioException();
//		if(estabelecimento.getEndereco().getLatitude().equals("")) throw new CampoObrigatorioException();
//		if(this.repositorioEstabelecimento.existe(estabelecimento.getNome())) throw new EstabelecimentoJaCadastradoException();
		
		String nome = estabelecimento.getNome();
		nome = Normalizer.normalize(nome, Normalizer.Form.NFD);
		nome = nome.replaceAll("[^\\p{ASCII}]", "");
		estabelecimento.setNome(nome);
		
		this.repositorioEstabelecimento.cadastrar(estabelecimento);
	} 
	public void atualizar(Estabelecimento estabelecimento) throws EstabelecimentoNaoEncontradoException, SQLException, CampoObrigatorioException{
		if(estabelecimento.getNome().equals("")) throw new CampoObrigatorioException();
		if(estabelecimento.getEndereco().getEndereco().equals("")) throw new CampoObrigatorioException();
//		if(estabelecimento.getEndereco().getLongitude().equals("")) throw new CampoObrigatorioException();
//		if(estabelecimento.getEndereco().getLatitude().equals("")) throw new CampoObrigatorioException();
		
		String nome = estabelecimento.getNome();
		nome = Normalizer.normalize(nome, Normalizer.Form.NFD);
		nome = nome.replaceAll("[^\\p{ASCII}]", "");
		estabelecimento.setNome(nome);
		
		this.repositorioEstabelecimento.atualizar(estabelecimento);
	}
	
	public void atualizarDisponibilidade(int id_estabelecimento) throws EstabelecimentoNaoEncontradoException, SQLException, CampoObrigatorioException{
		
		this.repositorioEstabelecimento.atualizarDisponibilidade(id_estabelecimento);
	}
	public void removerNome(String nome) throws EstabelecimentoNaoEncontradoException, SQLException{
		Estabelecimento estabelecimento = null;
		
		nome = Normalizer.normalize(nome, Normalizer.Form.NFD);
		nome = nome.replaceAll("[^\\p{ASCII}]", "");
		
		
		estabelecimento = this.repositorioEstabelecimento.procurarNome(nome);
		this.repositorioEstabelecimento.removerNome(nome);
	}
	public void removerId(int id_estabelecimento) throws EstabelecimentoNaoEncontradoException, SQLException{
		Estabelecimento estabelecimento = null;
		
		estabelecimento = this.repositorioEstabelecimento.procurarId(id_estabelecimento);
		this.repositorioEstabelecimento.removerId(id_estabelecimento);
	}
	
	public Estabelecimento procurarId(int id_estabelecimento) throws EstabelecimentoNaoEncontradoException, SQLException{
		return this.repositorioEstabelecimento.procurarId(id_estabelecimento);
	}
	
	public Estabelecimento procurarNome(String nome) throws EstabelecimentoNaoEncontradoException, SQLException{
		nome = Normalizer.normalize(nome, Normalizer.Form.NFD);
		nome = nome.replaceAll("[^\\p{ASCII}]", "");
		
		return this.repositorioEstabelecimento.procurarNome(nome);
	}
	
	public Estabelecimento procurarLogradouro(String logradouro) throws EstabelecimentoNaoEncontradoException, SQLException{
		return this.repositorioEstabelecimento.procurarLogradouro(logradouro);
	}
	
	public Estabelecimento procurarBairro(String bairro) throws EstabelecimentoNaoEncontradoException, SQLException{
		return this.repositorioEstabelecimento.procurarBairro(bairro);
	}
	
	public Estabelecimento procurarAvaliacao(String Avaliacao) throws EstabelecimentoNaoEncontradoException, SQLException{
		return this.repositorioEstabelecimento.procurarAvaliacao(Avaliacao);
	}
	
	
	public ArrayList<Estabelecimento> listar() throws SQLException{
		return this.repositorioEstabelecimento.listar();
	}
	
	public ArrayList<Estabelecimento> listarPendentes() throws SQLException{
		return this.repositorioEstabelecimento.listarPendentes();
	}
	
	public ArrayList<Estabelecimento> listarCadastrados(int id_user) throws SQLException{
		return this.repositorioEstabelecimento.listarCadastrados(id_user);
	}
	
	public ArrayList<Estabelecimento> listarCadastradosPendentes(int id_user) throws SQLException{
		return this.repositorioEstabelecimento.listarCadastradosPendentes(id_user);
	}
	
	public ArrayList<Estabelecimento> listarBuscaNome(String nome)
			throws CampoObrigatorioException, EstabelecimentoNaoEncontradoException, SQLException {
		nome = Normalizer.normalize(nome, Normalizer.Form.NFD);
		nome = nome.replaceAll("[^\\p{ASCII}]", "");
		
		return this.repositorioEstabelecimento.listarBuscaNome(nome);
	}
	
	public boolean checarDisponivel(String nome) throws EstabelecimentoNaoEncontradoException, SQLException {
		return this.repositorioEstabelecimento.checarDisponivel(nome);
	}
	
	public ArrayList<Estabelecimento> listarTodos() throws SQLException{
		return this.repositorioEstabelecimento.listarTodos();
	}
}
