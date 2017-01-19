package com.ejt.evento;

import java.sql.SQLException;
import java.text.Normalizer;
import java.util.ArrayList;

public class ControladorEvento {

	private IRepositorioEvento repositorioEvento;
	
	public ControladorEvento() throws Exception{
		this.repositorioEvento = new RepositorioEventoBDR();
		
	}
	
	public void cadastrar(Evento evento) throws CampoObrigatorioException, EventoJaCadastradoException, SQLException, EventoNaoEncontradoException{
		if(evento.getNome().equals("")) throw new CampoObrigatorioException();
		if(evento.getEndereco().getEndereco().equals("")) throw new CampoObrigatorioException();
//		if(evento.getEndereco().getLongitude().equals("")) throw new CampoObrigatorioException();
//		if(evento.getEndereco().getLatitude().equals("")) throw new CampoObrigatorioException();
//		if(this.repositorioEvento.existe(evento.getNome())) throw new EventoJaCadastradoException();
		
		String nome = evento.getNome();
		nome = Normalizer.normalize(nome, Normalizer.Form.NFD);
		nome = nome.replaceAll("[^\\p{ASCII}]", "");
		evento.setNome(nome);
		
		this.repositorioEvento.cadastrar(evento);
	}
	
	public void atualizar(Evento evento) throws CampoObrigatorioException, EventoNaoEncontradoException, SQLException{
		if(evento.getNome().equals("")) throw new CampoObrigatorioException();
		if(evento.getEndereco().getEndereco().equals("")) throw new CampoObrigatorioException();
//		if(evento.getEndereco().getLongitude().equals("")) throw new CampoObrigatorioException();
//		if(evento.getEndereco().getLatitude().equals("")) throw new CampoObrigatorioException();
		
		String nome = evento.getNome();
		nome = Normalizer.normalize(nome, Normalizer.Form.NFD);
		nome = nome.replaceAll("[^\\p{ASCII}]", "");
		evento.setNome(nome);
		this.repositorioEvento.atualizar(evento);
	}
	
	public void atualizarDisponibilidade(int id_evento) throws CampoObrigatorioException, EventoNaoEncontradoException, SQLException{	
		
		this.repositorioEvento.atualizarDisponibilidade(id_evento);
	}
	
	public void removerNome(String nome) throws CampoObrigatorioException, EventoNaoEncontradoException, SQLException{
		Evento evento = null;
		
		nome = Normalizer.normalize(nome, Normalizer.Form.NFD);
		nome = nome.replaceAll("[^\\p{ASCII}]", "");
		
		evento = this.repositorioEvento.procurarNome(nome);
		this.repositorioEvento.removerNome(nome);
	}
	
	public void removerId(int id_evento) throws CampoObrigatorioException, EventoNaoEncontradoException, SQLException{
		Evento evento = null;
		evento = this.repositorioEvento.procurarId(id_evento);
		
		this.repositorioEvento.removerId(id_evento);
	}
	
	public void removerData(String data) throws CampoObrigatorioException, EventoNaoEncontradoException, SQLException{
		Evento evento = null;
		evento = this.repositorioEvento.procurarData(data);
		
		this.repositorioEvento.removerData(data);;
	}
	
	
	public void removerLocal(String local) throws CampoObrigatorioException, EventoNaoEncontradoException, SQLException{
		Evento evento = null;
		evento = this.repositorioEvento.procurarLocal(local);
		
	}
	
	public Evento procurarNome(String nome) throws CampoObrigatorioException, EventoNaoEncontradoException, SQLException{
		nome = Normalizer.normalize(nome, Normalizer.Form.NFD);
		nome = nome.replaceAll("[^\\p{ASCII}]", "");
		
		
		return this.repositorioEvento.procurarNome(nome);
	}
	
	public Evento procurarId(int id_evento) throws CampoObrigatorioException, EventoNaoEncontradoException, SQLException{
		return this.repositorioEvento.procurarId(id_evento);
	}
	
	public Evento procurarData(String data) throws CampoObrigatorioException, EventoNaoEncontradoException, SQLException{
		return this.repositorioEvento.procurarData(data);
	}
	
	public Evento procurarLocal(String local) throws CampoObrigatorioException, EventoNaoEncontradoException, SQLException{
		local = Normalizer.normalize(local, Normalizer.Form.NFD);
		local = local.replaceAll("[^\\p{ASCII}]", "");
		
		
		return this.repositorioEvento.procurarLocal(local);
	}
	
	public Evento procurarLogradouro(String logradouro) throws CampoObrigatorioException, EventoNaoEncontradoException, SQLException{
		return this.repositorioEvento.procurarLogradouro(logradouro);
	}
	
	public Evento procurarBairro(String bairro) throws CampoObrigatorioException, EventoNaoEncontradoException, SQLException{
		return this.repositorioEvento.procurarBairro(bairro);
	}
	
	public ArrayList<Evento> listar() throws SQLException{
		return this.repositorioEvento.listar();
	}
	
	public ArrayList<Evento> listarTodos() throws SQLException{
		return this.repositorioEvento.listarTodos();
	}
	
	public ArrayList<Evento> listarPendentes() throws SQLException{
		return this.repositorioEvento.listarPendentes();
	}
	
	public ArrayList<Evento> listarCadastrados(int id_user) throws SQLException{
		return this.repositorioEvento.listarCadastrados(id_user);
	}
	
	public ArrayList<Evento> listarCadastradosPendentes(int id_user) throws SQLException{
		return this.repositorioEvento.listarCadastradosPendentes(id_user);
	}
	
	public ArrayList<Evento> listarBuscaNome(String nome) throws CampoObrigatorioException, EventoNaoEncontradoException, SQLException{
		nome = Normalizer.normalize(nome, Normalizer.Form.NFD);
		nome = nome.replaceAll("[^\\p{ASCII}]", "");
		
		return this.repositorioEvento.listarBuscaNome(nome);
	}
	
	public boolean checarDisponivel(String nome) throws CampoObrigatorioException, EventoNaoEncontradoException, SQLException{
		return this.repositorioEvento.checarDisponivel(nome);
	}
}
