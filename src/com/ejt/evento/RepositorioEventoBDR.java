package com.ejt.evento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.text.Normalizer;


import com.ejt.contato.Contato;
import com.ejt.endereco.Endereco;
import com.ejt.usuario.Usuario;
import com.ejt.util.Conexao;
import com.ejt.util.Database;

public class RepositorioEventoBDR implements IRepositorioEvento {

	private Connection conectar = null;

	public RepositorioEventoBDR() throws Exception {
		this.conectar = Conexao.getConnection(Database.MYSQL);
	}

	@Override
	public void cadastrar(Evento evento) throws CampoObrigatorioException, EventoJaCadastradoException, SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "insert into evento(nome, local, endereco, longitude, latitude, email, telefone, "
					+ "celular, data_evento, valor_ingresso, imagem, descricao, id_user, disponibilidade) "
					+ "values(?, ?, ?, ?, ?, ?, ?, ?, STR_TO_DATE(?, '%d/%m/%Y'), ?, ?, ?, ?, 'Pendente')";

			stmt = this.conectar.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, evento.getNome());
			stmt.setString(2, evento.getLocal());
			stmt.setString(3, evento.getEndereco().getEndereco());
			stmt.setString(4, evento.getEndereco().getLongitude());
			stmt.setString(5, evento.getEndereco().getLatitude());
			stmt.setString(6, evento.getContato().getEmail());
			stmt.setString(7, evento.getContato().getTelefone());
			stmt.setString(8, evento.getContato().getCelular());
			stmt.setString(9, evento.getData_evento());
			stmt.setFloat(10, evento.getValor_ingresso());
			stmt.setString(11, evento.getImagem());
			stmt.setString(12, evento.getDescricao());
			stmt.setInt(13, evento.getId_user());
			stmt.execute();
			rs = stmt.getGeneratedKeys();

			int id = 0;

			while (rs.next()) {
				id = rs.getInt(1);
			}

			evento.setId_evento(id);

		} finally {
			stmt.close();
		}
	}

	@Override
	public void atualizar(Evento evento) throws CampoObrigatorioException, EventoNaoEncontradoException, SQLException {
		PreparedStatement stmt = null;
		String sql;

		try {
			sql = "update evento set nome = ?, local = ?, endereco = ?, "
					+ "longitude = ?, latitude = ?, email = ?, telefone = ?, celular = ?, "
					//+ "data_evento = ?, valor_ingresso = ?, imagem = ?, descricao = ? "
					+ "valor_ingresso = ?, imagem = ?, descricao = ? "
					+ "where id_evento = ?";

			stmt = this.conectar.prepareStatement(sql);
			stmt.setString(1, evento.getNome());
			stmt.setString(2, evento.getLocal());
			stmt.setString(3, evento.getEndereco().getEndereco());
			stmt.setString(4, evento.getEndereco().getLongitude());
			stmt.setString(5, evento.getEndereco().getLatitude());
			stmt.setString(6, evento.getContato().getEmail());
			stmt.setString(7, evento.getContato().getTelefone());
			stmt.setString(8, evento.getContato().getCelular());
			//stmt.setString(9, evento.getData_evento());
			stmt.setFloat(9, evento.getValor_ingresso());
			stmt.setString(10, evento.getImagem());
			stmt.setString(11, evento.getDescricao());
			stmt.setInt(12, evento.getId_evento());
			Integer resultado = stmt.executeUpdate();
			if (resultado == 0) {
				throw new EventoNaoEncontradoException();
			}
		} finally {
			stmt.close();
		}
	}

	@Override
	public void removerNome(String nome) throws CampoObrigatorioException, EventoNaoEncontradoException, SQLException {
		PreparedStatement stmt = null;

		try {
			String sql = "delete from evento where nome = ?";
			stmt = this.conectar.prepareStatement(sql);
			stmt.setString(1, nome);
			stmt.execute();
		} finally {
			stmt.close();
		}
	}

	@Override
	public void removerData(String data) throws CampoObrigatorioException, EventoNaoEncontradoException, SQLException {
		PreparedStatement stmt = null;

		try {
			String sql = "delete from evento where data_evento = ?";
			stmt = this.conectar.prepareStatement(sql);
			stmt.setString(1, data);
			stmt.execute();
		} finally {
			stmt.close();
		}
	}

	@Override
	public void removerId(int id_evento) throws CampoObrigatorioException, EventoNaoEncontradoException, SQLException {
		PreparedStatement stmt = null;

		try {
			String sql = "delete from evento where id_evento = ?";
			stmt = this.conectar.prepareStatement(sql);
			stmt.setInt(1, id_evento);
			stmt.execute();
		} finally {
			stmt.close();
		}
	}

	@Override
	public void removerLocal(String local)
			throws CampoObrigatorioException, EventoNaoEncontradoException, SQLException {
		PreparedStatement stmt = null;
		
		try {
			String sql = "delete from evento where local = ?";
			stmt = this.conectar.prepareStatement(sql);
			stmt.setString(1, local);
			stmt.execute();
		} finally {
		stmt.close();
		}
	}

	@Override
	public Evento procurarNome(String nome)
			throws CampoObrigatorioException, EventoNaoEncontradoException, SQLException {
		Evento evento = null;
		String complemento = " where nome = " + "'"+nome+"'";
		ArrayList<Evento> eventos = this.listar(complemento);
		if(evento != null){
			throw new EventoNaoEncontradoException();
		}
		return eventos.get(0);
	}

	@Override
	public Evento procurarData(String data)
			throws CampoObrigatorioException, EventoNaoEncontradoException, SQLException {
		Evento evento = null;
		String complemento = " where data_evento = " + "'"+data+"'";
		ArrayList<Evento> eventos = this.listar(complemento);
		if(evento != null){
			throw new EventoNaoEncontradoException();
		}
		return eventos.get(0);
	}

	@Override
	public Evento procurarLocal(String local)
			throws CampoObrigatorioException, EventoNaoEncontradoException, SQLException {
		Evento evento = null;
		String complemento = " where local = " + "'"+local+"'";
		ArrayList<Evento> eventos = this.listar(complemento);
		if(evento != null){
			throw new EventoNaoEncontradoException();
		}
		return eventos.get(0);
	}

	@Override
	public Evento procurarLogradouro(String endereco)
			throws CampoObrigatorioException, EventoNaoEncontradoException, SQLException {
		Evento evento = null;
		String complemento = " where endereco = " + "'"+endereco+"'";
		ArrayList<Evento> eventos = this.listar(complemento);
		if(evento != null){
			throw new EventoNaoEncontradoException();
		}
		return eventos.get(0);
	}

	@Override
	public Evento procurarBairro(String latitude)
			throws CampoObrigatorioException, EventoNaoEncontradoException, SQLException {
		Evento evento = null;
		String complemento = " where latitude = " + "'"+latitude+"'";
		ArrayList<Evento> eventos = this.listar(complemento);
		if(evento != null){
			throw new EventoNaoEncontradoException();
		}
		return eventos.get(0);
	}

	@Override
	public Evento procurarId(int id_evento)
			throws CampoObrigatorioException, EventoNaoEncontradoException, SQLException {
		Evento evento = null;
		String complemento = " where id_evento = " + "'"+id_evento+"'";
		ArrayList<Evento> eventos = this.listar(complemento);
		if(evento != null){
			throw new EventoNaoEncontradoException();
		}
		return eventos.get(0);
	}

	@Override
	public boolean existe(String nome) throws CampoObrigatorioException, EventoNaoEncontradoException, SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "select count(*) as quantidade from "
					+ "evento where nome = ?";
			stmt = this.conectar.prepareStatement(sql);
			stmt.setString(1, nome);
			rs = stmt.executeQuery();
			rs.next();
			if (rs.getInt("quantidade") == 0) {
				return false;
			} else {
				return true;
			}
		} finally {
			stmt.close();
		}
		
	}

	@Override
	public ArrayList<Evento> listar() throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Evento> eventos = new ArrayList<Evento>();
		String sql;
		try {
			sql = "select * from evento where disponibilidade = 'Aprovado' order by data_evento asc;";
			stmt = this.conectar.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				Evento evento = new Evento(rs.getInt("id_evento"), rs.getString("nome"),
						rs.getString("local"), rs.getString("endereco"), rs.getString("longitude"), rs.getString("latitude"), rs.getString("email"), rs.getString("telefone"),
						rs.getString("celular"), rs.getString("data_evento"), rs.getFloat("valor_ingresso"), rs.getString("imagem"), rs.getString("descricao"), rs.getInt("id_user"));
				
				Endereco endereco = new Endereco();
				Contato contato = new Contato();

				endereco.setEndereco(rs.getString("endereco"));
				endereco.setLongitude(rs.getString("longitude"));
				endereco.setLatitude(rs.getString("latitude"));

				contato.setEmail(rs.getString("email"));
				contato.setTelefone(rs.getString("telefone"));
				contato.setCelular(rs.getString("celular"));
				
				evento.setContato(contato);
				evento.setEndereco(endereco);
				
				eventos.add(evento);
			}
		} finally {
			stmt.close();
			rs.close();
		}
		return eventos;
	}

	@Override
	public ArrayList<Evento> listar(String complemento) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Evento> eventos = new ArrayList<Evento>();
		String sql;
		try {
			sql = "select * from evento ";
			sql += complemento +" order by data_evento asc;";
			stmt = this.conectar.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				Evento evento = new Evento(rs.getInt("id_evento"), rs.getString("nome"),
						rs.getString("local"), rs.getString("endereco"), rs.getString("longitude"), rs.getString("latitude"), rs.getString("email"), rs.getString("telefone"),
						rs.getString("celular"), rs.getString("data_evento"), rs.getFloat("valor_ingresso"), rs.getString("imagem"), rs.getString("descricao"), rs.getInt("id_user"));
				
				Endereco endereco = new Endereco();
				Contato contato = new Contato();

				endereco.setEndereco(rs.getString("endereco"));
				endereco.setLongitude(rs.getString("longitude"));
				endereco.setLatitude(rs.getString("latitude"));

				contato.setEmail(rs.getString("email"));
				contato.setTelefone(rs.getString("telefone"));
				contato.setCelular(rs.getString("celular"));
				
				evento.setContato(contato);
				evento.setEndereco(endereco);
				
				eventos.add(evento);
			}
		} finally {
			stmt.close();
			rs.close();
		}
		return eventos;
	}

	@Override
	public ArrayList<Evento> listarCadastrados(int id_user) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Evento> eventos = new ArrayList<Evento>();
		String sql;
		try {
			sql = "select * from evento where id_user = "+id_user +" and disponibilidade = 'Aprovado' order by data_evento asc;";
			stmt = this.conectar.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				Evento evento = new Evento(rs.getInt("id_evento"), rs.getString("nome"),
						rs.getString("local"), rs.getString("endereco"), rs.getString("longitude"), rs.getString("latitude"), rs.getString("email"), rs.getString("telefone"),
						rs.getString("celular"), rs.getString("data_evento"), rs.getFloat("valor_ingresso"), rs.getString("imagem"), rs.getString("descricao"), rs.getInt("id_user"));
				
				Endereco endereco = new Endereco();
				Contato contato = new Contato();

				endereco.setEndereco(rs.getString("endereco"));
				endereco.setLongitude(rs.getString("longitude"));
				endereco.setLatitude(rs.getString("latitude"));

				contato.setEmail(rs.getString("email"));
				contato.setTelefone(rs.getString("telefone"));
				contato.setCelular(rs.getString("celular"));
				
				evento.setContato(contato);
				evento.setEndereco(endereco);
				
				eventos.add(evento);
			}
		} finally {
			stmt.close();
			rs.close();
		}
		return eventos;
		}

	@Override
	public ArrayList<Evento> listarCadastradosPendentes(int id_user) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Evento> eventos = new ArrayList<Evento>();
		String sql;
		try {
			sql = "select * from evento where id_user = "+id_user+ " and disponibilidade = 'Pendente' order by data_evento asc;";
			stmt = this.conectar.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				Evento evento = new Evento(rs.getInt("id_evento"), rs.getString("nome"),
						rs.getString("local"), rs.getString("endereco"), rs.getString("longitude"), rs.getString("latitude"), rs.getString("email"), rs.getString("telefone"),
						rs.getString("celular"), rs.getString("data_evento"), rs.getFloat("valor_ingresso"), rs.getString("imagem"), rs.getString("descricao"), rs.getInt("id_user"));
				
				Endereco endereco = new Endereco();
				Contato contato = new Contato();

				endereco.setEndereco(rs.getString("endereco"));
				endereco.setLongitude(rs.getString("longitude"));
				endereco.setLatitude(rs.getString("latitude"));

				contato.setEmail(rs.getString("email"));
				contato.setTelefone(rs.getString("telefone"));
				contato.setCelular(rs.getString("celular"));
				
				evento.setContato(contato);
				evento.setEndereco(endereco);
				
				eventos.add(evento);
			}
		} finally {
			stmt.close();
			rs.close();
		}
		return eventos;
	}

	@Override
	public ArrayList<Evento> listarPendentes() throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Evento> eventos = new ArrayList<Evento>();
		String sql;
		try {
			sql = "select * from evento where disponibilidade = 'Pendente' order by data_evento asc;";
			stmt = this.conectar.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				Evento evento = new Evento(rs.getInt("id_evento"), rs.getString("nome"),
						rs.getString("local"), rs.getString("endereco"), rs.getString("longitude"), rs.getString("latitude"), rs.getString("email"), rs.getString("telefone"),
						rs.getString("celular"), rs.getString("data_evento"), rs.getFloat("valor_ingresso"), rs.getString("imagem"), rs.getString("descricao"), rs.getInt("id_user"));
				
				Endereco endereco = new Endereco();
				Contato contato = new Contato();

				endereco.setEndereco(rs.getString("endereco"));
				endereco.setLongitude(rs.getString("longitude"));
				endereco.setLatitude(rs.getString("latitude"));

				contato.setEmail(rs.getString("email"));
				contato.setTelefone(rs.getString("telefone"));
				contato.setCelular(rs.getString("celular"));
				
				evento.setContato(contato);
				evento.setEndereco(endereco);
				
				eventos.add(evento);
			}
		} finally {
			stmt.close();
			rs.close();
		}
		return eventos;
	}

	@Override
	public ArrayList<Evento> listarBuscaNome(String nome)
			throws CampoObrigatorioException, EventoNaoEncontradoException, SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Evento> eventos = new ArrayList<Evento>();
		String sql;
		try {
			sql = "select * from evento where nome like '"+nome +"%' and disponibilidade = 'Aprovado' order by data_evento asc;";
			stmt = this.conectar.prepareStatement(sql);
			rs = stmt.executeQuery();

			
			while(rs.next()){
				
			
		         
		         
			Evento evento = new Evento(rs.getInt("id_evento"), rs.getString("nome"),
						rs.getString("local"), rs.getString("endereco"), rs.getString("longitude"), rs.getString("latitude"), rs.getString("email"), rs.getString("telefone"),
						rs.getString("celular"), rs.getString("data_evento"), rs.getFloat("valor_ingresso"), rs.getString("imagem"), rs.getString("descricao"), rs.getInt("id_user"));
				
				Endereco endereco = new Endereco();
				Contato contato = new Contato();

				endereco.setEndereco(rs.getString("endereco"));
				endereco.setLongitude(rs.getString("longitude"));
				endereco.setLatitude(rs.getString("latitude"));

				contato.setEmail(rs.getString("email"));
				contato.setTelefone(rs.getString("telefone"));
				contato.setCelular(rs.getString("celular"));
				
				evento.setContato(contato);
				evento.setEndereco(endereco);
				
				eventos.add(evento);
				
				
				
			}
		} finally {
			stmt.close();
			rs.close();
		}
		return eventos;
	}

	@Override
	public void atualizarDisponibilidade(int id_evento)
			throws CampoObrigatorioException, EventoNaoEncontradoException, SQLException {
		PreparedStatement stmt = null;
		String sql;

		try {
			sql = "update evento set disponibilidade = 'Aprovado' where id_evento = "+id_evento;
			stmt = this.conectar.prepareStatement(sql);
			//stmt.setInt(1, evento.getId_evento());
			Integer resultado = stmt.executeUpdate();
			if (resultado == 0) {
				throw new EventoNaoEncontradoException();
			}
		} finally {
			stmt.close();
		}
	}

	@Override
	public ArrayList<Evento> listarTodos() throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Evento> eventos = new ArrayList<Evento>();
		String sql;
		try {
			//sql = "select * from evento where disponibilidade = 'Aprovado' order by data_evento asc;";
			
			sql = "select evento.id_evento, evento.nome, evento.local, evento.endereco, evento.latitude, evento.longitude, "
					+ "evento.email, evento.telefone, evento.celular, evento.data_evento, evento.valor_ingresso, evento.descricao, evento.imagem, usuario.nome as user"
					+ " from evento, usuario "
					+ "where evento.id_user = usuario.id_user order by evento.nome ASC";
			stmt = this.conectar.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				Evento evento = new Evento(rs.getInt("id_evento"), rs.getString("nome"),
						rs.getString("local"), rs.getString("endereco"), rs.getString("longitude"), rs.getString("latitude"), rs.getString("email"), rs.getString("telefone"),
						rs.getString("celular"), rs.getString("data_evento"), rs.getFloat("valor_ingresso"), rs.getString("imagem"), rs.getString("descricao"), rs.getString("user"));
				
				Endereco endereco = new Endereco();
				Contato contato = new Contato();
				Usuario user = new Usuario();
				
				endereco.setEndereco(rs.getString("endereco"));
				endereco.setLongitude(rs.getString("longitude"));
				endereco.setLatitude(rs.getString("latitude"));

				contato.setEmail(rs.getString("email"));
				contato.setTelefone(rs.getString("telefone"));
				contato.setCelular(rs.getString("celular"));
				
				user.setNome(rs.getString("user"));
				
				evento.setContato(contato);
				evento.setEndereco(endereco);
				evento.setUser(user);
				
				eventos.add(evento);
			}
		} finally {
			stmt.close();
			rs.close();
		}
		return eventos;
	}

	@Override
	public boolean checarDisponivel(String nome)
			throws CampoObrigatorioException, EventoNaoEncontradoException, SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "select count(*) as quantidade from " 
					+ "evento where Disponibilidade = 'Aprovado' and nome = ?";
			stmt = this.conectar.prepareStatement(sql);
			stmt.setString(1, nome);
			rs = stmt.executeQuery();
			rs.next();
			if (rs.getInt("quantidade") == 0) {
				return false;
			} else {
				return true;
			}
		} finally {
			stmt.close();
		}
	}


}


