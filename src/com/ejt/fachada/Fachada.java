package com.ejt.fachada;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ejt.adm.Adm;
import com.ejt.adm.ControladorAdm;
import com.ejt.avaliacao.Avaliacao;
import com.ejt.avaliacao.AvaliacaoNaoEncontradaException;
import com.ejt.avaliacao.CampoObrigatorioException;
import com.ejt.avaliacao.ControladorAvaliacao;
import com.ejt.estabelecimento.ControladorEstabelecimento;
import com.ejt.estabelecimento.Estabelecimento;
import com.ejt.estabelecimento.EstabelecimentoJaCadastradoException;
import com.ejt.estabelecimento.EstabelecimentoNaoEncontradoException;
import com.ejt.evento.ControladorEvento;
import com.ejt.evento.Evento;
import com.ejt.evento.EventoJaCadastradoException;
import com.ejt.evento.EventoNaoEncontradoException;
import com.ejt.usuario.ControladorUsuario;
import com.ejt.usuario.Usuario;

public class Fachada {

	private static Fachada instance = null;

	private Fachada() {
	}

	public static Fachada getInstance() {
		if (Fachada.instance == null) {
			Fachada.instance = new Fachada();
		}

		return Fachada.instance;
	}

	private ControladorUsuario controladorUsuario;
	private ControladorAvaliacao controladorAvaliacao;
	private ControladorEstabelecimento controladorEstabelecimento;
	private ControladorEvento controladorEvento;
	private ControladorAdm controladorAdm;

	public void avaliacaoCadastra(Avaliacao avaliacao) throws Exception {
		this.controladorAvaliacao = new ControladorAvaliacao();
		this.controladorAvaliacao.cadastrar(avaliacao);
	}

	public void avaliacaoAtualizar(Avaliacao avaliacao) throws Exception {
		this.controladorAvaliacao = new ControladorAvaliacao();
		this.controladorAvaliacao.atualizar(avaliacao);
	}

	public boolean avaliacaoExiste(int id_user, int id_estabelecimento) throws Exception {
		this.controladorAvaliacao = new ControladorAvaliacao();
		return this.controladorAvaliacao.existe(id_user, id_estabelecimento);
	}

	public void avaliacaoRemover(int id_user, int id_estabelecimento) throws Exception {
		this.controladorAvaliacao = new ControladorAvaliacao();
		this.controladorAvaliacao.remover(id_user, id_estabelecimento);
	}

	public ArrayList<Avaliacao> avaliacaoListar(int id_estabelecimento) throws Exception {
		this.controladorAvaliacao = new ControladorAvaliacao();
		return this.controladorAvaliacao.listar(id_estabelecimento);
	}

	public boolean admLogin(String email, String senha) throws Exception {
		this.controladorAdm = new ControladorAdm();
		return this.controladorAdm.login(email, senha);
	}

	public void admCadastra(Adm adm) throws Exception {
		this.controladorAdm = new ControladorAdm();
		this.controladorAdm.cadastrar(adm);
	}

	public Adm admProcurarNome(String nome) throws Exception {
		this.controladorAdm = new ControladorAdm();
		return this.controladorAdm.procurarNome(nome);
	}

	public Adm admProcurarEmail(String email) throws Exception {
		this.controladorAdm = new ControladorAdm();
		return this.controladorAdm.procurarEmail(email);
	}

	public Adm admProcurarId(int id_adm) throws Exception {
		this.controladorAdm = new ControladorAdm();
		return this.controladorAdm.procurarId(id_adm);
	}

	public void admRemoverEmail(String email) throws Exception {
		this.controladorAdm = new ControladorAdm();
		this.controladorAdm.removerEmail(email);
		;
	}

	public void admRemoverId(int id_adm) throws Exception {
		this.controladorAdm = new ControladorAdm();
		this.controladorAdm.remover(id_adm);
	}

	public void admAtualizar(Adm adm) throws Exception {
		this.controladorAdm = new ControladorAdm();
		this.controladorAdm.atualizar(adm);
	}

	public ArrayList<Adm> admListar() throws Exception {
		this.controladorAdm = new ControladorAdm();
		return this.controladorAdm.listar();
	}

	public boolean usuarioLogin(String email, String senha) throws Exception {
		this.controladorUsuario = new ControladorUsuario();
		return this.controladorUsuario.login(email, senha);
	}

	public void usuarioCadastrar(Usuario usuario) throws Exception {
		this.controladorUsuario = new ControladorUsuario();
		this.controladorUsuario.cadastrar(usuario);
	}

	public Usuario usuarioProcurarNome(String nome) throws Exception {
		this.controladorUsuario = new ControladorUsuario();
		return this.controladorUsuario.procurarNome(nome);
	}

	public Usuario usuarioProcurarEmail(String email) throws Exception {
		this.controladorUsuario = new ControladorUsuario();
		return this.controladorUsuario.procurarEmail(email);
	}

	public Usuario usuarioProcurarId(int id_user) throws Exception {
		this.controladorUsuario = new ControladorUsuario();
		return this.controladorUsuario.procurarId(id_user);
	}

	public void usuarioRemover(String email) throws Exception {
		this.controladorUsuario = new ControladorUsuario();
		this.controladorUsuario.remover(email);
	}

	public void usuarioRemoverId(int id_user) throws Exception {
		this.controladorUsuario = new ControladorUsuario();
		this.controladorUsuario.removerId(id_user);
	}

	public void usuarioAtualizar(Usuario usuario) throws Exception {
		this.controladorUsuario = new ControladorUsuario();
		this.controladorUsuario.atualizar(usuario);
	}

	public ArrayList<Usuario> usuarioListar() throws Exception {
		this.controladorUsuario = new ControladorUsuario();
		return this.controladorUsuario.listar();
	}

	public void estabelecimentoCadastrar(Estabelecimento estabelecimento) throws Exception {
		this.controladorEstabelecimento = new ControladorEstabelecimento();
		this.controladorEstabelecimento.cadastrar(estabelecimento);
	}

	public void estabelecimentoAtualizar(Estabelecimento estabelecimento) throws Exception {
		this.controladorEstabelecimento = new ControladorEstabelecimento();
		this.controladorEstabelecimento.atualizar(estabelecimento);
	}

	public void estabelecimentoAtualizarDisponibilidade(int id_estabelecimento) throws Exception {
		this.controladorEstabelecimento = new ControladorEstabelecimento();
		this.controladorEstabelecimento.atualizarDisponibilidade(id_estabelecimento);
	}

	public void estabelecimentoRemoverNome(String nome) throws Exception {
		this.controladorEstabelecimento = new ControladorEstabelecimento();
		this.controladorEstabelecimento.removerNome(nome);
	}

	public void estabelecimentoRemoverId(int id_estabelecimento) throws Exception {
		this.controladorEstabelecimento = new ControladorEstabelecimento();
		this.controladorEstabelecimento.removerId(id_estabelecimento);
	}

	public Estabelecimento estabelecimentoProcurarNome(String nome) throws Exception {
		this.controladorEstabelecimento = new ControladorEstabelecimento();
		return this.controladorEstabelecimento.procurarNome(nome);
	}

	public Estabelecimento estabelecimentoProcurarId(int id_estabelecimento) throws Exception {
		this.controladorEstabelecimento = new ControladorEstabelecimento();
		return this.controladorEstabelecimento.procurarId(id_estabelecimento);
	}

	public Estabelecimento estabelecimentoProcurarLogradouro(String logradouro) throws Exception {
		this.controladorEstabelecimento = new ControladorEstabelecimento();
		return this.controladorEstabelecimento.procurarLogradouro(logradouro);
	}

	public Estabelecimento estabelecimentoProcurarBairro(String bairro) throws Exception {
		this.controladorEstabelecimento = new ControladorEstabelecimento();
		return this.controladorEstabelecimento.procurarBairro(bairro);
	}

	public Estabelecimento estabelecimentoProcurarAvaliacao(String Avaliacao) throws Exception {
		this.controladorEstabelecimento = new ControladorEstabelecimento();
		return this.controladorEstabelecimento.procurarAvaliacao(Avaliacao);
	}

	public ArrayList<Estabelecimento> estabelecimentoListar() throws Exception {
		this.controladorEstabelecimento = new ControladorEstabelecimento();
		return this.controladorEstabelecimento.listar();
	}

	public ArrayList<Estabelecimento> estabelecimentoListarTodos() throws Exception {
		this.controladorEstabelecimento = new ControladorEstabelecimento();
		return this.controladorEstabelecimento.listarTodos();
	}

	public boolean estabelecimentoChecarDisponivel(String nome) throws Exception {
		this.controladorEstabelecimento = new ControladorEstabelecimento();
		return this.controladorEstabelecimento.checarDisponivel(nome);
	}

	public ArrayList<Estabelecimento> estabelecimentoListarBuscaNome(String nome) throws Exception {
		this.controladorEstabelecimento = new ControladorEstabelecimento();
		return this.controladorEstabelecimento.listarBuscaNome(nome);
	}

	public ArrayList<Estabelecimento> estabelecimentoListarPendentes() throws Exception {
		this.controladorEstabelecimento = new ControladorEstabelecimento();
		return this.controladorEstabelecimento.listarPendentes();
	}

	public ArrayList<Estabelecimento> estabelecimentoListarCadastrados(int id_user) throws Exception {
		this.controladorEstabelecimento = new ControladorEstabelecimento();
		return this.controladorEstabelecimento.listarCadastrados(id_user);
	}

	public ArrayList<Estabelecimento> estabelecimentoListarCadastradosPendentes(int id_user) throws Exception {
		this.controladorEstabelecimento = new ControladorEstabelecimento();
		return this.controladorEstabelecimento.listarCadastradosPendentes(id_user);
	}

	public void eventoCadastrar(Evento evento) throws Exception {
		this.controladorEvento = new ControladorEvento();
		this.controladorEvento.cadastrar(evento);
	}

	public void eventoAtualizar(Evento evento) throws Exception {
		this.controladorEvento = new ControladorEvento();
		this.controladorEvento.atualizar(evento);
	}

	public void eventoAtualizarDisponibilidade(int id_evento) throws Exception {
		this.controladorEvento = new ControladorEvento();
		this.controladorEvento.atualizarDisponibilidade(id_evento);
	}

	public void eventoRemoverNome(String nome) throws Exception {
		this.controladorEvento = new ControladorEvento();
		this.controladorEvento.removerNome(nome);
	}

	public void eventoRemoverId(int id_evento) throws Exception {
		this.controladorEvento = new ControladorEvento();
		this.controladorEvento.removerId(id_evento);
	}

	public void eventoRemoverData(String data) throws Exception {
		this.controladorEvento = new ControladorEvento();
		this.controladorEvento.removerData(data);
	}

	public void eventoRemoverLocal(String local) throws Exception {
		this.controladorEvento = new ControladorEvento();
		this.controladorEvento.removerLocal(local);
	}

	public Evento eventoProcurarNome(String nome) throws Exception {
		this.controladorEvento = new ControladorEvento();
		return this.controladorEvento.procurarNome(nome);
	}

	public Evento eventoProcurarId(int id_evento) throws Exception {
		this.controladorEvento = new ControladorEvento();
		return this.controladorEvento.procurarId(id_evento);
	}

	public Evento eventoProcurarData(String data) throws Exception {
		this.controladorEvento = new ControladorEvento();
		return this.controladorEvento.procurarData(data);
	}

	public Evento eventoProcurarLocal(String local) throws Exception {

		this.controladorEvento = new ControladorEvento();
		return this.controladorEvento.procurarLocal(local);
	}

	public Evento eventoProcurarLogradouro(String logradouro) throws Exception {
		this.controladorEvento = new ControladorEvento();
		return this.controladorEvento.procurarLogradouro(logradouro);
	}

	public Evento eventoProcurarBairro(String bairro) throws Exception {
		this.controladorEvento = new ControladorEvento();
		return this.controladorEvento.procurarBairro(bairro);
	}

	public ArrayList<Evento> eventoListar() throws Exception {
		this.controladorEvento = new ControladorEvento();
		return this.controladorEvento.listar();
	}

	public ArrayList<Evento> eventoListarPendentes() throws Exception {
		this.controladorEvento = new ControladorEvento();
		return this.controladorEvento.listarPendentes();
	}

	public ArrayList<Evento> eventoListarCadastrados(int id_user) throws Exception {
		this.controladorEvento = new ControladorEvento();
		return this.controladorEvento.listarCadastrados(id_user);
	}

	public ArrayList<Evento> eventoListarCadastradosPendentes(int id_user) throws Exception {
		this.controladorEvento = new ControladorEvento();
		return this.controladorEvento.listarCadastradosPendentes(id_user);
	}

	public ArrayList<Evento> eventoListarBuscaNome(String nome) throws Exception {
		this.controladorEvento = new ControladorEvento();
		return this.controladorEvento.listarBuscaNome(nome);
	}
	
	public boolean eventoChecarDisponivel(String nome) throws Exception {
		this.controladorEvento = new ControladorEvento();
		return this.controladorEvento.checarDisponivel(nome);
	}
	
	public ArrayList<Evento> eventoListarTodos() throws Exception {
		this.controladorEvento = new ControladorEvento();
		return this.controladorEvento.listarTodos();
	}
}
