package com.ejt.evento;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IRepositorioEvento {

	public void cadastrar(Evento evento) throws CampoObrigatorioException, EventoJaCadastradoException, SQLException;
	public void atualizar(Evento evento) throws CampoObrigatorioException, EventoNaoEncontradoException, SQLException;
	public void atualizarDisponibilidade(int id_evento) throws CampoObrigatorioException, EventoNaoEncontradoException, SQLException;
	public void removerNome(String nome) throws CampoObrigatorioException, EventoNaoEncontradoException, SQLException;
	public void removerData(String data) throws CampoObrigatorioException, EventoNaoEncontradoException, SQLException;
	public void removerId(int id_evento) throws CampoObrigatorioException, EventoNaoEncontradoException, SQLException;
	public void removerLocal(String local) throws CampoObrigatorioException, EventoNaoEncontradoException, SQLException;
	public Evento procurarNome(String nome) throws CampoObrigatorioException, EventoNaoEncontradoException, SQLException;
	public Evento procurarId(int id_evento) throws CampoObrigatorioException, EventoNaoEncontradoException, SQLException;
	public Evento procurarData(String data) throws CampoObrigatorioException, EventoNaoEncontradoException, SQLException;
	public Evento procurarLocal(String local) throws CampoObrigatorioException, EventoNaoEncontradoException, SQLException;
	public Evento procurarLogradouro(String logradouro) throws CampoObrigatorioException, EventoNaoEncontradoException, SQLException;
	public Evento procurarBairro(String bairro) throws CampoObrigatorioException, EventoNaoEncontradoException, SQLException;
	public boolean existe(String nome) throws CampoObrigatorioException, EventoNaoEncontradoException, SQLException;
	public boolean checarDisponivel(String nome) throws CampoObrigatorioException, EventoNaoEncontradoException, SQLException;
	public ArrayList<Evento> listar() throws SQLException;
	public ArrayList<Evento> listarPendentes() throws SQLException;
	public ArrayList<Evento> listar(String complemento) throws SQLException;
	public ArrayList<Evento> listarCadastrados(int id_user) throws SQLException;
	public ArrayList<Evento> listarCadastradosPendentes(int id_user) throws SQLException;
	public ArrayList<Evento> listarBuscaNome(String nome) throws CampoObrigatorioException, EventoNaoEncontradoException, SQLException;
	public ArrayList<Evento> listarTodos() throws SQLException;
	
}
