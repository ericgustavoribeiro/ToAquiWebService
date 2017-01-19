package com.ejt.usuario;

import java.awt.font.FontRenderContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ejt.util.Conexao;
import com.ejt.util.Database;

public class RepositorioUsuarioBDR implements IRepositorioUsuario {

	private Connection conectar = null;
	
	
	
	public RepositorioUsuarioBDR() throws Exception {
		this.conectar = Conexao.getConnection(Database.MYSQL);
	}

	@Override
	public void cadastrar(Usuario usuario)
			throws UsuarioJaCadastradoException, CampoObrigatorioException, SenhaInvalidaException, SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql;
		try{
			sql = "insert into usuario(nome, email, senha) "
					+ "values(?,?,?)";
			
			stmt = this.conectar.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getEmail());
			stmt.setString(3, usuario.getSenha());
			stmt.execute();
			rs = stmt.getGeneratedKeys();
			int id = 1;
			
			while(rs.next()){
				id = rs.getInt(1);
			}
			
			usuario.setId_user(id);
			
		}finally{
			stmt.close();
		}
		
	}

	@Override
	public void atualizar(Usuario usuario)
			throws CampoObrigatorioException, SenhaInvalidaException, UsuarioNaoEncontradoException, SQLException {
		PreparedStatement stmt = null;
		String sql;
		try{
			if(usuario != null){
				sql = "update usuario set nome = ?, senha = ? "
						+ "where id_user = ?";
				stmt = this.conectar.prepareStatement(sql);
				stmt.setString(1, usuario.getNome());
				stmt.setString(2, usuario.getSenha());
				stmt.setInt(3, usuario.getId_user());
				Integer resultado = stmt.executeUpdate();
				if(resultado == 0){
					throw new UsuarioNaoEncontradoException();
				}
			}
		}finally{
			stmt.close();
		}
	}

	@Override
	public void removerId(int id_user) throws UsuarioNaoEncontradoException, SQLException {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		try{
			String sql = "delete from usuario where id_user = ?";
			stmt = this.conectar.prepareStatement(sql);
			stmt.setInt(1, id_user);
			stmt.execute();
		}finally{
			stmt.close();
		}
	}
	
	@Override
	public void remover(String email) throws UsuarioNaoEncontradoException, SQLException {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		try{
			String sql = "delete from usuario where email = ?";
			stmt = this.conectar.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.execute();
		}finally{
			stmt.close();
		}
	}
	
	@Override
	public Usuario procurarNome(String nome) throws UsuarioNaoEncontradoException, SQLException {
		Usuario usuario = null;
		String complemento = "where nome = " + "'"+nome+"'";
		ArrayList<Usuario> usuarios = this.listar(complemento);
		if(usuario != null){
			throw new UsuarioNaoEncontradoException();
		}
		
		return usuarios.get(0);
	}

	@Override
	public Usuario procurarId(int id_user) throws UsuarioNaoEncontradoException, SQLException {
		// TODO Auto-generated method stub
		Usuario usuario = null;
		String complemento = "where id_user = " + id_user;
		ArrayList<Usuario> usuarios = this.listar(complemento);
		if(usuario != null){
			throw new UsuarioNaoEncontradoException();
		}
		
		return usuarios.get(0);
	}
	
	@Override
	public Usuario procurarEmail(String email) throws UsuarioNaoEncontradoException, SQLException {
		Usuario usuario = null;
		String complemento = "where email = " + "'"+email+"'";
		ArrayList<Usuario> usuarios = this.listar(complemento);
		if(usuario != null){
			throw new UsuarioNaoEncontradoException();
		}
		
		return usuarios.get(0);
	}

	@Override
	public boolean existe(String email) throws UsuarioNaoEncontradoException, SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			String sql = "select count(*) as quantidade from "
					+ "usuario where email = ?";
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
	public ArrayList<Usuario> listar() throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		String sql;
		try{
			sql = "select * from usuario ";
			stmt = this.conectar.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				Usuario usuario = new Usuario(rs.getInt("id_user"),
						rs.getString("nome"),
						rs.getString("email"));
			usuarios.add(usuario);
			}
		}finally{
			stmt.close();
			rs.close();
		}
		
		
		return usuarios;
	}

	@Override
	public ArrayList<Usuario> listar(String complemento) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		String sql;
		try{
			sql = "select * from usuario ";
			sql += complemento;
			stmt = this.conectar.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				Usuario usuario = new Usuario(rs.getInt("id_user"),
						rs.getString("nome"),
						rs.getString("email"));
			
				usuarios.add(usuario);			
				}
		}finally{
			stmt.close();
			//rs.close();
		}
		
		
		return usuarios;
	}

	@Override
	public boolean login(String email, String senha) throws UsuarioNaoEncontradoException, SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			String sql ="select count(*) as existe from usuario where email = ? and senha = ?";
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

	

	

}
