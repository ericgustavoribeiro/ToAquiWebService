package com.ejt.adm;

public class Adm {

	private int id_adm;
	private String nome;
	private String email;
	private String senha;
	
	public Adm(int id_adm, String nome, String email) {
		super();
		this.id_adm = id_adm;
		this.nome = nome;
		this.email = email;
	}

	public Adm(int id_adm, String nome, String email, String senha) {
		super();
		this.id_adm = id_adm;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}

	public int getId_adm() {
		return id_adm;
	}

	public void setId_adm(int id_adm) {
		this.id_adm = id_adm;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
