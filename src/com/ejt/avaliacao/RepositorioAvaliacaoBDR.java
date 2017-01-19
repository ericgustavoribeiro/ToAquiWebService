package com.ejt.avaliacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ejt.usuario.Usuario;
import com.ejt.util.Conexao;
import com.ejt.util.Database;

public class RepositorioAvaliacaoBDR implements IRepositorioAvaliacao{

	private Connection conectar = null;
	
	
	
	public RepositorioAvaliacaoBDR() throws Exception {
		super();
		this.conectar = Conexao.getConnection(Database.MYSQL);
	}

	@Override
	public void cadastrar(Avaliacao avaliacao)
			throws AvaliacaoJaCadastradaException, CampoObrigatorioException, SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql;
		try{
			sql = "insert into avaliacao(id_user, id_estabelecimento, nota, descricao) "
					+ "values(?,?,?,?)";
			stmt = this.conectar.prepareStatement(sql);
			stmt.setInt(1, avaliacao.getId_user());
			stmt.setInt(2, avaliacao.getId_estabelecimento());
			stmt.setInt(3, avaliacao.getNota());
			stmt.setString(4, avaliacao.getDescricao());
			stmt.execute();
			
		}finally{
			stmt.close();
			
		}
		
	}

	@Override
	public void atualizar(Avaliacao avaliacao) throws CampoObrigatorioException, SQLException, AvaliacaoNaoEncontradaException {
		PreparedStatement stmt = null;
		String sql;
		try{
		if(avaliacao != null){
			sql = "update avaliacao set nota = ?, descricao = ? "
					+ "where id_user = ? and id_estabelecimento = ?";
			stmt = this.conectar.prepareStatement(sql);
			stmt.setInt(1, avaliacao.getNota());
			stmt.setString(2, avaliacao.getDescricao());
			stmt.setInt(3, avaliacao.getId_user());
			stmt.setInt(4, avaliacao.getId_estabelecimento());
			Integer resultado = stmt.executeUpdate();
			if(resultado == 0){
				throw new AvaliacaoNaoEncontradaException();
			}
			
		
		}
		}finally{
			stmt.close();
		}
	}

	@Override
	public void remover(int id_user, int id_estabelecimento) throws CampoObrigatorioException, SQLException {
		PreparedStatement stmt = null;
		try{
			String sql = "delete from avaliacao where id_user = ? and id_estabelecimento = ?";
			
			stmt = this.conectar.prepareStatement(sql);
			stmt.setInt(1, id_user);
			stmt.setInt(2, id_estabelecimento);
			stmt.execute();
			
			
		}finally{
			stmt.close();
		}
		
	}

	@Override
	public boolean existe(int id_user, int id_estabelecimento) throws CampoObrigatorioException, SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			String sql = "select count(*) as quantidade from avaliacao where id_user = ? and id_estabelecimento = ?";
			stmt = this.conectar.prepareStatement(sql);
			stmt.setInt(1, id_user);
			stmt.setInt(2, id_estabelecimento);
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

//	@Override
//	public ArrayList<Avaliacao> listar() throws SQLException {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	@Override
	public ArrayList<Avaliacao> listar(int id_estabelecimento) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();
		String sql;
		try{
			sql = "select avaliacao.id_user, avaliacao.id_estabelecimento, usuario.nome, avaliacao.nota, avaliacao.descricao "
					+ "from avaliacao, usuario "
					+ "where avaliacao.id_estabelecimento = "+ id_estabelecimento +" and avaliacao.id_user = usuario.id_user";
			stmt = this.conectar.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				Avaliacao avaliacao = new Avaliacao(rs.getString("nome"), 
						rs.getInt("nota"), rs.getString("descricao"));
				
				Usuario usuario = new Usuario();
				usuario.setNome(rs.getString("nome"));
				
				avaliacao.setId_user(rs.getInt("id_user"));
				avaliacao.setId_estabelecimento(rs.getInt("id_estabelecimento"));
				avaliacao.setUsuario(usuario);
				
				avaliacoes.add(avaliacao);
			}
			
		}finally{
			stmt.close();
			rs.close();
		}
		return avaliacoes;
	}

	@Override
	public Avaliacao procurar(int id_user, int id_estabelecimento)
			throws CampoObrigatorioException, SQLException, AvaliacaoNaoEncontradaException {
		Avaliacao avaliacao = null;
		String complemento = " where id_estabelecimento = "+ id_estabelecimento+" and id_user = " +id_user;
		ArrayList<Avaliacao> avaliacoes = this.listar(complemento);
		if(avaliacoes.isEmpty()){
			throw new AvaliacaoNaoEncontradaException();
		}
		
		return avaliacoes.get(0);
	}

	@Override
	public ArrayList<Avaliacao> listar(String complemento) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();
		String sql;
		try{
			sql = "select * from avaliacao";
			sql += complemento;
			stmt = this.conectar.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				Avaliacao avaliacao = new Avaliacao(rs.getInt("id_user"), 
						rs.getInt("id_estabelecimento"), rs.getInt("nota"), 
						rs.getString("descricao"));
				
				avaliacoes.add(avaliacao);
			}
		}finally{
			stmt.close();
		}
		return avaliacoes;
	}
	
}
