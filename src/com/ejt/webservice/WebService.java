package com.ejt.webservice;

import java.io.Serializable;
import java.util.ArrayList;

import com.ejt.avaliacao.Avaliacao;
import com.ejt.avaliacao.ControladorAvaliacao;
import com.ejt.estabelecimento.Estabelecimento;
import com.ejt.evento.ControladorEvento;
import com.ejt.evento.Evento;
import com.ejt.fachada.Fachada;
import com.ejt.usuario.ControladorUsuario;
import com.ejt.usuario.Usuario;

public class WebService{

	
	
	private Fachada fachada;

	public WebService() {
	}

	public void avaliacaoCadastra(Avaliacao avaliacao) throws Exception {
		this.fachada = Fachada.getInstance();
		this.fachada.avaliacaoCadastra(avaliacao);
	}

	public void avaliacaoAtualizar(Avaliacao avaliacao) throws Exception {
		this.fachada = Fachada.getInstance();
		this.fachada.avaliacaoAtualizar(avaliacao);
	}

	public boolean avaliacaoExiste(int id_user, int id_estabelecimento) throws Exception {
		this.fachada = Fachada.getInstance();
		return this.fachada.avaliacaoExiste(id_user, id_estabelecimento);
	}

	public void avaliacaoRemover(int id_user, int id_estabelecimento) throws Exception {
		this.fachada = Fachada.getInstance();
		this.fachada.avaliacaoRemover(id_user, id_estabelecimento);
	}

	public ArrayList<Avaliacao> avaliacaoListar(int id_estabelecimento) throws Exception {
		this.fachada = Fachada.getInstance();
		return this.fachada.avaliacaoListar(id_estabelecimento);
	}

	public boolean usuarioLogin(String email, String senha) throws Exception {
		this.fachada = Fachada.getInstance();
		return this.fachada.usuarioLogin(email, senha);
	}

	public void usuarioCadastrar(Usuario usuario) throws Exception {
		this.fachada = Fachada.getInstance();
		this.fachada.usuarioCadastrar(usuario);
	}

	public Usuario usuarioProcurarNome(String nome) throws Exception {
		this.fachada = Fachada.getInstance();
		return this.fachada.usuarioProcurarNome(nome);
	}

	public Usuario usuarioProcurarEmail(String email) throws Exception {
		this.fachada = Fachada.getInstance();
		return this.fachada.usuarioProcurarEmail(email);
	}

	public Usuario usuarioProcurarId(int id_user) throws Exception {
		this.fachada = Fachada.getInstance();
		return this.fachada.usuarioProcurarId(id_user);
	}

	public void usuarioRemover(String email) throws Exception {
		this.fachada = Fachada.getInstance();
		this.fachada.usuarioRemover(email);
	}

	public void usuarioRemoverId(int id_user) throws Exception {
		this.fachada = Fachada.getInstance();
		this.fachada.usuarioRemoverId(id_user);
	}

	public void usuarioAtualizar(Usuario usuario) throws Exception {
		this.fachada = Fachada.getInstance();
		this.fachada.usuarioAtualizar(usuario);
	}

	public ArrayList<Usuario> usuarioListar() throws Exception {
		this.fachada = Fachada.getInstance();
		return this.fachada.usuarioListar();
	}

	public void estabelecimentoCadastrar(Estabelecimento estabelecimento) throws Exception {
		System.out.println("Webservice Cadastrar");
		this.fachada = Fachada.getInstance();
		this.fachada.estabelecimentoCadastrar(estabelecimento);
	}

	public void estabelecimentoAtualizar(Estabelecimento estabelecimento) throws Exception {
		this.fachada = Fachada.getInstance();
		this.fachada.estabelecimentoAtualizar(estabelecimento);
	}

	public void estabelecimentoAtualizarDisponibilidade(int id_estabelecimento) throws Exception {
		this.fachada = Fachada.getInstance();
		this.fachada.estabelecimentoAtualizarDisponibilidade(id_estabelecimento);
	}

	public void estabelecimentoRemoverNome(String nome) throws Exception {
		this.fachada = Fachada.getInstance();
		this.fachada.estabelecimentoRemoverNome(nome);
	}

	public void estabelecimentoRemoverId(int id_estabelecimento) throws Exception {
		this.fachada = Fachada.getInstance();
		this.fachada.estabelecimentoRemoverId(id_estabelecimento);
	}

	public Estabelecimento estabelecimentoProcurarNome(String nome) throws Exception {
		this.fachada = Fachada.getInstance();
		return this.fachada.estabelecimentoProcurarNome(nome);
	}

	public Estabelecimento estabelecimentoProcurarId(int id_estabelecimento) throws Exception {
		System.out.println("Webservice Procurar");
		this.fachada = Fachada.getInstance();
		return this.fachada.estabelecimentoProcurarId(id_estabelecimento);
	}

	public Estabelecimento estabelecimentoProcurarLogradouro(String logradouro) throws Exception {
		this.fachada = Fachada.getInstance();
		return this.fachada.estabelecimentoProcurarLogradouro(logradouro);
	}

	public Estabelecimento estabelecimentoProcurarBairro(String bairro) throws Exception {
		this.fachada = Fachada.getInstance();
		return this.fachada.estabelecimentoProcurarBairro(bairro);
	}

	public Estabelecimento estabelecimentoProcurarAvaliacao(String Avaliacao) throws Exception {
		this.fachada = Fachada.getInstance();
		return this.fachada.estabelecimentoProcurarAvaliacao(Avaliacao);
	}

	public ArrayList<Estabelecimento> estabelecimentoListar() throws Exception {
		this.fachada = Fachada.getInstance();
		return this.fachada.estabelecimentoListar();
	}

	public ArrayList<Estabelecimento> estabelecimentoListarTodos() throws Exception {
		this.fachada = Fachada.getInstance();
		return this.fachada.estabelecimentoListarTodos();
	}

	public boolean estabelecimentoChecarDisponivel(String nome) throws Exception {
		this.fachada = Fachada.getInstance();
		return this.fachada.estabelecimentoChecarDisponivel(nome);
	}

	public ArrayList<Estabelecimento> estabelecimentoListarBuscaNome(String nome) throws Exception {
		System.out.println("ESTAB Buscar Nome : " +nome);
		this.fachada = Fachada.getInstance();
		return this.fachada.estabelecimentoListarBuscaNome(nome);
	}

	public ArrayList<Estabelecimento> estabelecimentoListarPendentes() throws Exception {
		this.fachada = Fachada.getInstance();
		return this.fachada.estabelecimentoListarPendentes();
	}

	public ArrayList<Estabelecimento> estabelecimentoListarCadastrados(int id_user) throws Exception {
		this.fachada = Fachada.getInstance();
		return this.fachada.estabelecimentoListarCadastrados(id_user);
	}

	public ArrayList<Estabelecimento> estabelecimentoListarCadastradosPendentes(int id_user) throws Exception {
		this.fachada = Fachada.getInstance();
		return this.fachada.estabelecimentoListarCadastradosPendentes(id_user);
	}

	public void eventoCadastrar(Evento evento) throws Exception {
		System.out.println("Dentro do Cadastrar Evento");
		this.fachada = Fachada.getInstance();
		this.fachada.eventoCadastrar(evento);
	}

	public void eventoAtualizar(Evento evento) throws Exception {
		this.fachada = Fachada.getInstance();
		this.fachada.eventoAtualizar(evento);
	}

	public void eventoAtualizarDisponibilidade(int id_evento) throws Exception {
		this.fachada = Fachada.getInstance();
		this.fachada.eventoAtualizarDisponibilidade(id_evento);
	}

	public void eventoRemoverNome(String nome) throws Exception {
		this.fachada = Fachada.getInstance();
		this.fachada.eventoRemoverNome(nome);
	}

	public void eventoRemoverId(int id_evento) throws Exception {
		this.fachada = Fachada.getInstance();
		this.fachada.eventoRemoverId(id_evento);
	}

	public void eventoRemoverData(String data) throws Exception {
		this.fachada = Fachada.getInstance();
		this.fachada.eventoRemoverData(data);
	}

	public void eventoRemoverLocal(String local) throws Exception {
		this.fachada = Fachada.getInstance();
		this.fachada.eventoRemoverLocal(local);
	}

	public Evento eventoProcurarNome(String nome) throws Exception {
		this.fachada = Fachada.getInstance();
		return this.fachada.eventoProcurarNome(nome);
	}

	public Evento eventoProcurarId(int id_evento) throws Exception {
		this.fachada = Fachada.getInstance();
		return this.fachada.eventoProcurarId(id_evento);
	}

	public Evento eventoProcurarData(String data) throws Exception {
		this.fachada = Fachada.getInstance();
		return this.fachada.eventoProcurarData(data);
	}

	public Evento eventoProcurarLocal(String local) throws Exception {
		this.fachada = Fachada.getInstance();
		return this.fachada.eventoProcurarLocal(local);
	}

	public Evento eventoProcurarLogradouro(String logradouro) throws Exception {
		this.fachada = Fachada.getInstance();
		return this.fachada.eventoProcurarLogradouro(logradouro);
	}

	public Evento eventoProcurarBairro(String bairro) throws Exception {
		this.fachada = Fachada.getInstance();
		return this.fachada.eventoProcurarBairro(bairro);
	}

	public ArrayList<Evento> eventoListar() throws Exception {
		this.fachada = Fachada.getInstance();
		return this.fachada.eventoListar();
	}

	public ArrayList<Evento> eventoListarPendentes() throws Exception {
		this.fachada = Fachada.getInstance();
		return this.fachada.eventoListarPendentes();
	}

	public ArrayList<Evento> eventoListarCadastrados(int id_user) throws Exception {
		this.fachada = Fachada.getInstance();
		return this.fachada.eventoListarCadastrados(id_user);
	}

	public ArrayList<Evento> eventoListarCadastradosPendentes(int id_user) throws Exception {
		this.fachada = Fachada.getInstance();
		return this.fachada.eventoListarCadastradosPendentes(id_user);
	}

	public ArrayList<Evento> eventoListarBuscaNome(String nome) throws Exception {
		System.out.println("EVENTO Buscar Nome : " +nome);
		this.fachada = Fachada.getInstance();
		return this.fachada.eventoListarBuscaNome(nome);
	}

	public boolean eventoChecarDisponivel(String nome) throws Exception {
		this.fachada = Fachada.getInstance();
		return this.fachada.eventoChecarDisponivel(nome);
	}

	public ArrayList<Evento> eventoListarTodos() throws Exception {
		this.fachada = Fachada.getInstance();
		return this.fachada.eventoListarTodos();
	}

	
}
