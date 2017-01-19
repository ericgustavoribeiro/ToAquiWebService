package com.ejt.adm;

import java.sql.SQLException;
import java.util.ArrayList;

public class ControladorAdm {

	private IRepositorioAdm repositorioAdm;

	public ControladorAdm() throws Exception {
		repositorioAdm = new RepositorioAdmBDR();
	}

	public void cadastrar(Adm adm) throws AdmJaCadastradoException, CampoObrigatorioException, SQLException {
		if (adm.getNome().equals(""))
			throw new CampoObrigatorioException();
		if (adm.getEmail().equals(""))
			throw new CampoObrigatorioException();
		if (adm.getSenha().equals(""))
			throw new CampoObrigatorioException();
		if (this.repositorioAdm.existe(adm.getEmail()))
			throw new AdmJaCadastradoException();

		this.repositorioAdm.cadastrar(adm);
	}

	public void remover(int id_adm) throws AdmNaoEncontradoException, CampoObrigatorioException, SQLException {
		this.repositorioAdm.procurarId(id_adm);
		this.repositorioAdm.remover(id_adm);
	}

	public void removerEmail(String email) throws AdmNaoEncontradoException, CampoObrigatorioException, SQLException {
		this.repositorioAdm.removerEmail(email);
		this.repositorioAdm.removerEmail(email);

	}

	public void atualizar(Adm adm)
			throws AdmNaoEncontradoException, AdmJaCadastradoException, CampoObrigatorioException, SQLException {
		if (adm.getNome().equals(""))
			throw new CampoObrigatorioException();
		if (adm.getEmail().equals(""))
			throw new CampoObrigatorioException();
		if (adm.getSenha().equals(""))
			throw new CampoObrigatorioException();
		if (this.repositorioAdm.existe(adm.getEmail()))
			throw new AdmJaCadastradoException();

		this.repositorioAdm.cadastrar(adm);
	}

	public boolean login(String email, String senha)
			throws SenhaInvalidaException, CampoObrigatorioException, SQLException, AdmNaoEncontradoException {
		this.repositorioAdm.procurarEmail(email);
		return this.repositorioAdm.login(email, senha);
	}

	public Adm procurarEmail(String email) throws AdmNaoEncontradoException, CampoObrigatorioException, SQLException {
		return this.repositorioAdm.procurarEmail(email);
	}

	public Adm procurarNome(String nome) throws AdmNaoEncontradoException, CampoObrigatorioException, SQLException {
		return this.repositorioAdm.procurarNome(nome);
	}

	public Adm procurarId(int id_adm) throws AdmNaoEncontradoException, CampoObrigatorioException, SQLException {
		return this.repositorioAdm.procurarId(id_adm);
	}

	public ArrayList<Adm> listar() throws SQLException {
		return this.repositorioAdm.listar();
	}

}
