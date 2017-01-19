package com.ejt.adm;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IRepositorioAdm {

	public void cadastrar(Adm adm) throws AdmJaCadastradoException, CampoObrigatorioException, SQLException;
	public void remover(int id_adm) throws AdmNaoEncontradoException, CampoObrigatorioException, SQLException;
	public void removerEmail(String email) throws AdmNaoEncontradoException, CampoObrigatorioException, SQLException;
	public void atualizar(Adm adm) throws AdmNaoEncontradoException, AdmJaCadastradoException, CampoObrigatorioException, SQLException;
	public boolean login(String email, String senha) throws SenhaInvalidaException, CampoObrigatorioException, SQLException;;
	public Adm procurarEmail(String email) throws AdmNaoEncontradoException, CampoObrigatorioException, SQLException;
	public Adm procurarNome(String nome) throws AdmNaoEncontradoException, CampoObrigatorioException, SQLException;
	public Adm procurarId(int id_adm) throws AdmNaoEncontradoException, CampoObrigatorioException, SQLException;
	public boolean existe(String email) throws AdmJaCadastradoException, CampoObrigatorioException, SQLException;
	public ArrayList<Adm> listar() throws SQLException;
	public ArrayList<Adm> listar(String complemento) throws SQLException;
	
	}
