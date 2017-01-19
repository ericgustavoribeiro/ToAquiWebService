package com.ejt.adm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ejt.usuario.Usuario;
import com.ejt.usuario.UsuarioNaoEncontradoException;
import com.ejt.util.Conexao;
import com.ejt.util.Database;

public class RepositorioAdmBDR implements IRepositorioAdm{

	private Connection conectar = null;
	
	
	
	public RepositorioAdmBDR() throws Exception {
		this.conectar = Conexao.getConnection(Database.MYSQL);
	}



	@Override
	public void cadastrar(Adm adm) throws AdmJaCadastradoException, CampoObrigatorioException, SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql;
		try{
			sql = "insert into adm(nome, email, senha) "
					+ "values(?,?,?)";
			
			stmt = this.conectar.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, adm.getNome());
			stmt.setString(2, adm.getEmail());
			stmt.setString(3, adm.getSenha());
			stmt.execute();
			rs = stmt.getGeneratedKeys();
			int id = 1;
			
			while(rs.next()){
				id = rs.getInt(1);
			}
			
			adm.setId_adm(id);
			
		}finally{
			stmt.close();
		}
	}



	@Override
	public void remover(int id_adm) throws AdmNaoEncontradoException, CampoObrigatorioException, SQLException {
		PreparedStatement stmt = null;
		try{
			String sql = "delete from adm where id_adm = ?";
			stmt = this.conectar.prepareStatement(sql);
			stmt.setInt(1, id_adm);
			stmt.execute();
		}finally{
			stmt.close();
		}
	}



	@Override
	public void removerEmail(String email) throws AdmNaoEncontradoException, CampoObrigatorioException, SQLException {
		PreparedStatement stmt = null;
		try{
			String sql = "delete from adm where email = ?";
			stmt = this.conectar.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.execute();
		}finally{
			stmt.close();
		}
		
	}



	@Override
	public void atualizar(Adm adm)
			throws AdmNaoEncontradoException, AdmJaCadastradoException, CampoObrigatorioException, SQLException {
		PreparedStatement stmt = null;
		String sql;
		try{
			if(adm != null){
				sql = "update adm set nome = ?, email = ?, senha = ? "
						+ "where id_adm = ?";
				stmt = this.conectar.prepareStatement(sql);
				stmt.setString(1, adm.getNome());
				stmt.setString(2, adm.getEmail());
				stmt.setString(3, adm.getSenha());
				stmt.setInt(4, adm.getId_adm());
				Integer resultado = stmt.executeUpdate();
				if(resultado == 0){
					throw new AdmNaoEncontradoException();
				}
			}
		}finally{
			stmt.close();
		}
	}



	@Override
	public boolean login(String email, String senha)
			throws SenhaInvalidaException, CampoObrigatorioException, SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			String sql ="select count(*) as existe from adm where email = ? and senha = ?";
			stmt = this.conectar.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.setString(2, senha);
			rs = stmt.executeQuery();
			rs.next();
			if(rs.getInt("existe") == 0){
				return false;
				
			}else {
				return true;
			}
		}finally{
			
		}
	}



	@Override
	public Adm procurarEmail(String email) throws AdmNaoEncontradoException, CampoObrigatorioException, SQLException {
		Adm adm = null;
		String complemento = " where email = '"+email+"'";
		ArrayList<Adm> adms = this.listar(complemento);
		if(adm != null){
			throw new AdmNaoEncontradoException();
		}
		return adms.get(0);
	}



	@Override
	public Adm procurarNome(String nome) throws AdmNaoEncontradoException, CampoObrigatorioException, SQLException {
		Adm adm = null;
		String complemento = " where nome = '"+nome+"'";
		ArrayList<Adm> adms = this.listar(complemento);
		if(adm != null){
			throw new AdmNaoEncontradoException();
		}
		return adms.get(0);
	}



	@Override
	public Adm procurarId(int id_adm) throws AdmNaoEncontradoException, CampoObrigatorioException, SQLException {
		Adm adm = null;
		String complemento = " where id_adm = " + id_adm;
		ArrayList<Adm> adms = this.listar(complemento);
		if(adm != null){
			throw new AdmNaoEncontradoException();
		}
		return adms.get(0);
	}



	@Override
	public boolean existe(String email) throws AdmJaCadastradoException, CampoObrigatorioException, SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			String sql = "select count(*) as quantidade from "
					+ "adm where email = ?";
			stmt = this.conectar.prepareStatement(sql);
			stmt.setString(1, email);
			rs = stmt.executeQuery();
			rs.next();
			
			if(rs.getInt("quantidade") == 0){
				return false;
			}else{
				
				return true;
			}
			
			
		}finally{
			stmt.close();
		}
	}



	@Override
	public ArrayList<Adm> listar() throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Adm> adms = new ArrayList<Adm>();
		String sql;
		try{
			sql = "select * from adm ";
			stmt = this.conectar.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				Adm adm= new Adm(rs.getInt("id_adm"),
						rs.getString("nome"),
						rs.getString("email"));
			adms.add(adm);
			}
		}finally{
			stmt.close();
			rs.close();
		}
		
		
		return adms;
	}



	@Override
	public ArrayList<Adm> listar(String complemento) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Adm> adms = new ArrayList<Adm>();
		String sql;
		try{
			sql = "select * from adm ";
			sql += complemento;
			stmt = this.conectar.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				Adm adm= new Adm(rs.getInt("id_adm"),
						rs.getString("nome"),
						rs.getString("email"));
			adms.add(adm);
			}
		}finally{
			stmt.close();
			rs.close();
		}
		
		
		return adms;
	}

	

}
