package com.ejt.estabelecimento;

import java.awt.font.FontRenderContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ejt.contato.Contato;
import com.ejt.endereco.Endereco;
import com.ejt.usuario.Usuario;
import com.ejt.util.Conexao;
import com.ejt.util.Database;

public class RepositorioEstabelecimentoBDR implements IRepositorioEstabelecimento {

	private Connection conectar = null;

	public RepositorioEstabelecimentoBDR() throws Exception {
		this.conectar = Conexao.getConnection(Database.MYSQL);
	}

	@Override
	public void cadastrar(Estabelecimento estabelecimento)
			throws EstabelecimentoJaCadastradoException, CampoObrigatorioException, SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql;
		try {
			//ORACLE
//			sql = "insert into estabelecimento(nome, logradouro, numero, bairro, email, telefone, "
//					+ "celular, categoria, pagamento_visa, pagamento_master, pagamento_cielo, pagamento_hiper, "
//					+ "pagamento_outro, descricao, imagem, id_user) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			sql = "insert into estabelecimento(nome, endereco, longitude, latitude, email, telefone, "
					+ "celular, categoria, pagamento_visa, pagamento_master, pagamento_cielo, pagamento_hiper, "
					+ "pagamento_outro, descricao, imagem, id_user, disponibilidade) "
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, 'Pendente')";
			
			
			stmt = this.conectar.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, estabelecimento.getNome());
			stmt.setString(2, estabelecimento.getEndereco().getEndereco());
			stmt.setString(3, estabelecimento.getEndereco().getLongitude());
			stmt.setString(4, estabelecimento.getEndereco().getLatitude());
			stmt.setString(5, estabelecimento.getContato().getEmail());
			stmt.setString(6, estabelecimento.getContato().getTelefone());
			stmt.setString(7, estabelecimento.getContato().getCelular());
			stmt.setString(8, estabelecimento.getCategoria());
			stmt.setString(9, estabelecimento.getPagamento_visa());
			stmt.setString(10, estabelecimento.getPagamento_master());
			stmt.setString(11, estabelecimento.getPagamento_cielo());
			stmt.setString(12, estabelecimento.getPagamento_hiper());
			stmt.setString(13, estabelecimento.getPagamento_outro());
			stmt.setString(14, estabelecimento.getDescricao());
			stmt.setString(15, estabelecimento.getImagem());
			stmt.setInt(16, estabelecimento.getId_user());
			stmt.execute();
			rs = stmt.getGeneratedKeys();
			int id = 1;
		
			while (rs.next()) {
				id = rs.getInt(1);
			}

			estabelecimento.setId_estabelecimento(id);

		} finally {
			stmt.close();
		}
	}

	@Override
	public void atualizar(Estabelecimento estabelecimento) throws EstabelecimentoNaoEncontradoException, SQLException {
		// TODO Auto-generated method stub
		PreparedStatement stmt = null;
		String sql;

		try {
			if (estabelecimento != null) {
				sql = "update estabelecimento set nome = ?, endereco = ?, longitude = ?, latitude = ?, email = ?,"
						+ " telefone = ?, celular = ?, categoria = ?, pagamento_visa = ?, "
						+ "pagamento_master = ?, pagamento_cielo = ?, pagamento_hiper = ?, "
						+ "pagamento_outro = ?, descricao = ?, imagem = ? where id_estabelecimento = ?";

				stmt = this.conectar.prepareStatement(sql);
				stmt.setString(1, estabelecimento.getNome());
				stmt.setString(2, estabelecimento.getEndereco().getEndereco());
				stmt.setString(3, estabelecimento.getEndereco().getLongitude());
				stmt.setString(4, estabelecimento.getEndereco().getLatitude());
				stmt.setString(5, estabelecimento.getContato().getEmail());
				stmt.setString(6, estabelecimento.getContato().getTelefone());
				stmt.setString(7, estabelecimento.getContato().getCelular());
				stmt.setString(8, estabelecimento.getCategoria());
				stmt.setString(9, estabelecimento.getPagamento_visa());
				stmt.setString(10, estabelecimento.getPagamento_master());
				stmt.setString(11, estabelecimento.getPagamento_cielo());
				stmt.setString(12, estabelecimento.getPagamento_hiper());
				stmt.setString(13, estabelecimento.getPagamento_outro());
				stmt.setString(14, estabelecimento.getDescricao());
				stmt.setString(15, estabelecimento.getImagem());
				stmt.setInt(16, estabelecimento.getId_estabelecimento());
				Integer resultado = stmt.executeUpdate();
				if (resultado == 0) {
					throw new EstabelecimentoNaoEncontradoException();
				}

			}

		} finally {
			stmt.close();
		}
	}

	@Override
	public void removerNome(String nome) throws EstabelecimentoNaoEncontradoException, SQLException {
		PreparedStatement stmt = null;
		try {
			String sql = "delete from estabelecimento where nome = ?";
			stmt = this.conectar.prepareStatement(sql);
			stmt.setString(1, nome);
			stmt.execute();

		} finally {
			stmt.close();
		}

	}

	@Override
	public void removerId(int id_estabelecimento) throws EstabelecimentoNaoEncontradoException, SQLException {
		PreparedStatement stmt = null;
		try {
			String sql = "delete from estabelecimento where id_estabelecimento = ?";
			stmt = this.conectar.prepareStatement(sql);
			stmt.setInt(1, id_estabelecimento);
			stmt.execute();

		} finally {
			stmt.close();
		}

	}

	@Override
	public void removerEmail(String email) throws EstabelecimentoNaoEncontradoException, SQLException {
		PreparedStatement stmt = null;
		try {
			String sql = "delete from estabelecimento where email = ?";
			stmt = this.conectar.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.execute();

		} finally {
			stmt.close();
		}

	}

	@Override
	public Estabelecimento procurarNome(String nome) throws EstabelecimentoNaoEncontradoException, SQLException {
		Estabelecimento estabelecimento = null;
		String complemento = " where nome = " + "'"+nome+"'";
		ArrayList<Estabelecimento> estabelecimentos = this.listar(complemento);

		if (estabelecimento != null) {
			throw new EstabelecimentoNaoEncontradoException();
		}

		return estabelecimentos.get(0);
	}
	
	@Override
	public Estabelecimento procurarId(int id_estabelecimento)
			throws EstabelecimentoNaoEncontradoException, SQLException {
		Estabelecimento estabelecimento = null;
		String complemento = " where id_estabelecimento = " + id_estabelecimento;
		ArrayList<Estabelecimento> estabelecimentos = this.listar(complemento);

		if (estabelecimento != null) {
			throw new EstabelecimentoNaoEncontradoException();
		}

		return estabelecimentos.get(0);
	}

	@Override
	public Estabelecimento procurarLogradouro(String logradouro)
			throws EstabelecimentoNaoEncontradoException, SQLException {
		Estabelecimento estabelecimento = null;
		String complemento = " where logradouro = " + "'"+logradouro+"'";
		ArrayList<Estabelecimento> estabelecimentos = this.listar(complemento);

		if (estabelecimento != null) {
			throw new EstabelecimentoNaoEncontradoException();
		}

		return estabelecimentos.get(0);
	}

	@Override
	public Estabelecimento procurarBairro(String bairro) throws EstabelecimentoNaoEncontradoException, SQLException {
		Estabelecimento estabelecimento = null;
		String complemento = " where bairro = " + "'"+bairro+"'";
		ArrayList<Estabelecimento> estabelecimentos = this.listar(complemento);

		if (estabelecimento != null) {
			throw new EstabelecimentoNaoEncontradoException();
		}

		return estabelecimentos.get(0);
	}

	@Override
	public Estabelecimento procurarAvaliacao(String Avaliacao)
			throws EstabelecimentoNaoEncontradoException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existe(String nome) throws EstabelecimentoNaoEncontradoException, SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "select count(*) as quantidade from " 
					+ "estabelecimento where nome = ?";
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
	public ArrayList<Estabelecimento> listar() throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Estabelecimento> estabelecimentos = new ArrayList<Estabelecimento>();
		String sql;
		try {
			sql = "Select * from estabelecimento where disponibilidade = 'Aprovado'";
			//sql = "Select * from estabelecimento ";
			stmt = this.conectar.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Estabelecimento estabelecimento = new Estabelecimento(rs.getInt("id_estabelecimento"),rs.getString("nome"), rs.getString("endereco"),
						rs.getString("longitude"), rs.getString("latitude"), rs.getString("email"), rs.getString("telefone"),
						rs.getString("celular"), rs.getString("categoria"), rs.getString("pagamento_visa"),
						rs.getString("pagamento_master"), rs.getString("pagamento_cielo"),
						rs.getString("pagamento_hiper"), rs.getString("pagamento_outro"), rs.getString("descricao"),
						rs.getString("imagem"));

				Endereco endereco = new Endereco();
				Contato contato = new Contato();

				endereco.setEndereco(rs.getString("endereco"));
				endereco.setLongitude(rs.getString("longitude"));
				endereco.setLatitude(rs.getString("latitude"));

				contato.setEmail(rs.getString("email"));
				contato.setTelefone(rs.getString("telefone"));
				contato.setCelular(rs.getString("celular"));

				estabelecimento.setEndereco(endereco);
				estabelecimento.setContato(contato);

				estabelecimentos.add(estabelecimento);

			}

		} finally {
			stmt.close();
			rs.close();
		}

		return estabelecimentos;
	}

	@Override
	public ArrayList<Estabelecimento> listar(String complemento) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Estabelecimento> estabelecimentos = new ArrayList<Estabelecimento>();
		String sql;
		try {
			sql = "Select * from estabelecimento ";
			sql += complemento;
			stmt = this.conectar.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Estabelecimento estabelecimento = new Estabelecimento(rs.getInt("id_estabelecimento"), rs.getString("nome"), rs.getString("endereco"),
						rs.getString("longitude"), rs.getString("latitude"), rs.getString("email"), rs.getString("telefone"),
						rs.getString("celular"), rs.getString("categoria"), rs.getString("pagamento_visa"),
						rs.getString("pagamento_master"), rs.getString("pagamento_cielo"),
						rs.getString("pagamento_hiper"), rs.getString("pagamento_outro"), rs.getString("descricao"),
						rs.getString("imagem"), rs.getInt("id_user"));

				Endereco endereco = new Endereco();
				Contato contato = new Contato();

				endereco.setEndereco(rs.getString("endereco"));
				endereco.setLongitude(rs.getString("longitude"));
				endereco.setLatitude(rs.getString("latitude"));

				contato.setEmail(rs.getString("email"));
				contato.setTelefone(rs.getString("telefone"));
				contato.setCelular(rs.getString("celular"));

				estabelecimento.setEndereco(endereco);
				estabelecimento.setContato(contato);

				estabelecimentos.add(estabelecimento);

			}

		} finally {
			stmt.close();
			rs.close();
		}

		return estabelecimentos;
	}

	@Override
	public ArrayList<Estabelecimento> listarCadastrados(int id_user) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Estabelecimento> estabelecimentos = new ArrayList<Estabelecimento>();
		String sql;
		try {
			sql = "Select * from estabelecimento where id_user = "+ id_user+" and disponibilidade = 'Aprovado'";
			stmt = this.conectar.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Estabelecimento estabelecimento = new Estabelecimento(rs.getInt("id_estabelecimento"), rs.getString("nome"), rs.getString("endereco"),
						rs.getString("longitude"), rs.getString("latitude"), rs.getString("email"), rs.getString("telefone"),
						rs.getString("celular"), rs.getString("categoria"), rs.getString("pagamento_visa"),
						rs.getString("pagamento_master"), rs.getString("pagamento_cielo"),
						rs.getString("pagamento_hiper"), rs.getString("pagamento_outro"), rs.getString("descricao"),
						rs.getString("imagem"), rs.getInt("id_user"));

				Endereco endereco = new Endereco();
				Contato contato = new Contato();

				endereco.setEndereco(rs.getString("endereco"));
				endereco.setLongitude(rs.getString("longitude"));
				endereco.setLatitude(rs.getString("latitude"));

				contato.setEmail(rs.getString("email"));
				contato.setTelefone(rs.getString("telefone"));
				contato.setCelular(rs.getString("celular"));

				estabelecimento.setEndereco(endereco);
				estabelecimento.setContato(contato);

				estabelecimentos.add(estabelecimento);

			}

		} finally {
			stmt.close();
			rs.close();
		}

		return estabelecimentos;
	}

	@Override
	public ArrayList<Estabelecimento> listarCadastradosPendentes(int id_user) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Estabelecimento> estabelecimentos = new ArrayList<Estabelecimento>();
		String sql;
		try {
			sql = "Select * from estabelecimento where id_user = "+ id_user +" and disponibilidade = 'Pendente'";
			stmt = this.conectar.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Estabelecimento estabelecimento = new Estabelecimento(rs.getInt("id_estabelecimento"), rs.getString("nome"), rs.getString("endereco"),
						rs.getString("longitude"), rs.getString("latitude"), rs.getString("email"), rs.getString("telefone"),
						rs.getString("celular"), rs.getString("categoria"), rs.getString("pagamento_visa"),
						rs.getString("pagamento_master"), rs.getString("pagamento_cielo"),
						rs.getString("pagamento_hiper"), rs.getString("pagamento_outro"), rs.getString("descricao"),
						rs.getString("imagem"), rs.getInt("id_user"));

				Endereco endereco = new Endereco();
				Contato contato = new Contato();

				endereco.setEndereco(rs.getString("endereco"));
				endereco.setLongitude(rs.getString("longitude"));
				endereco.setLatitude(rs.getString("latitude"));

				contato.setEmail(rs.getString("email"));
				contato.setTelefone(rs.getString("telefone"));
				contato.setCelular(rs.getString("celular"));

				estabelecimento.setEndereco(endereco);
				estabelecimento.setContato(contato);

				estabelecimentos.add(estabelecimento);

			}

		} finally {
			stmt.close();
			rs.close();
		}

		return estabelecimentos;
	}

	@Override
	public ArrayList<Estabelecimento> listarPendentes() throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Estabelecimento> estabelecimentos = new ArrayList<Estabelecimento>();
		String sql;
		try {
			sql = "Select * from estabelecimento where disponibilidade = 'Pendente'";
			stmt = this.conectar.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Estabelecimento estabelecimento = new Estabelecimento(rs.getString("nome"), rs.getString("endereco"),
						rs.getString("longitude"), rs.getString("latitude"), rs.getString("email"), rs.getString("telefone"),
						rs.getString("celular"), rs.getString("categoria"), rs.getString("pagamento_visa"),
						rs.getString("pagamento_master"), rs.getString("pagamento_cielo"),
						rs.getString("pagamento_hiper"), rs.getString("pagamento_outro"), rs.getString("descricao"),
						rs.getString("imagem"));

				Endereco endereco = new Endereco();
				Contato contato = new Contato();

				endereco.setEndereco(rs.getString("endereco"));
				endereco.setLongitude(rs.getString("longitude"));
				endereco.setLatitude(rs.getString("latitude"));

				contato.setEmail(rs.getString("email"));
				contato.setTelefone(rs.getString("telefone"));
				contato.setCelular(rs.getString("celular"));

				estabelecimento.setEndereco(endereco);
				estabelecimento.setContato(contato);

				estabelecimentos.add(estabelecimento);

			}

		} finally {
			stmt.close();
			rs.close();
		}

		return estabelecimentos;
	}

	@Override
	public ArrayList<Estabelecimento> listarBuscaNome(String nome)
			throws CampoObrigatorioException, EstabelecimentoNaoEncontradoException, SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Estabelecimento> estabelecimentos = new ArrayList<Estabelecimento>();
		String sql;
		try {
			sql = "Select * from estabelecimento where nome like '"+ nome+"%' and disponibilidade = 'Aprovado'";
			stmt = this.conectar.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Estabelecimento estabelecimento = new Estabelecimento(rs.getInt("id_estabelecimento"), rs.getString("nome"), rs.getString("endereco"),
						rs.getString("longitude"), rs.getString("latitude"), rs.getString("email"), rs.getString("telefone"),
						rs.getString("celular"), rs.getString("categoria"), rs.getString("pagamento_visa"),
						rs.getString("pagamento_master"), rs.getString("pagamento_cielo"),
						rs.getString("pagamento_hiper"), rs.getString("pagamento_outro"), rs.getString("descricao"),
						rs.getString("imagem"), rs.getInt("id_user"));

				Endereco endereco = new Endereco();
				Contato contato = new Contato();

				endereco.setEndereco(rs.getString("endereco"));
				endereco.setLongitude(rs.getString("longitude"));
				endereco.setLatitude(rs.getString("latitude"));

				contato.setEmail(rs.getString("email"));
				contato.setTelefone(rs.getString("telefone"));
				contato.setCelular(rs.getString("celular"));

				estabelecimento.setEndereco(endereco);
				estabelecimento.setContato(contato);

				estabelecimentos.add(estabelecimento);

			}

		} finally {
			stmt.close();
			rs.close();
		}

		return estabelecimentos;
	}

	@Override
	public void atualizarDisponibilidade(int id_estabelecimento)
			throws EstabelecimentoNaoEncontradoException, SQLException {
		PreparedStatement stmt = null;
		String sql;

		try {
				sql = "update estabelecimento set disponibilidade = 'Aprovado' where id_estabelecimento = "+id_estabelecimento;
				stmt = this.conectar.prepareStatement(sql);
				//stmt.setInt(1, estabelecimento.getId_estabelecimento());
				Integer resultado = stmt.executeUpdate();
				if (resultado == 0) {
					throw new EstabelecimentoNaoEncontradoException();
				}

			

		} finally {
			stmt.close();
		}
	}

	@Override
	public boolean checarDisponivel(String nome) throws EstabelecimentoNaoEncontradoException, SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "select count(*) as quantidade from " 
					+ "estabelecimento where Disponibilidade = 'Aprovado' and nome = ?";
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
	public ArrayList<Estabelecimento> listarTodos() throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Estabelecimento> estabelecimentos = new ArrayList<Estabelecimento>();
		String sql;
		try {
			sql = "Select estabelecimento.id_estabelecimento, estabelecimento.nome, estabelecimento.endereco, estabelecimento.latitude, estabelecimento.longitude, "
					+ "estabelecimento.email, estabelecimento.telefone, estabelecimento.celular, estabelecimento.categoria, estabelecimento.pagamento_visa, "
					+ "estabelecimento.pagamento_master, estabelecimento.pagamento_cielo, estabelecimento.pagamento_hiper, estabelecimento.pagamento_outro,"
					+ "estabelecimento.descricao, estabelecimento.imagem, usuario.nome as user "
					+ "from estabelecimento, usuario "
					+ "where estabelecimento.id_user = usuario.id_user order by estabelecimento.nome ASC";
			stmt = this.conectar.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Estabelecimento estabelecimento = new Estabelecimento(rs.getInt("id_estabelecimento"),rs.getString("nome"), rs.getString("endereco"),
						rs.getString("longitude"), rs.getString("latitude"), rs.getString("email"), rs.getString("telefone"),
						rs.getString("celular"), rs.getString("categoria"), rs.getString("pagamento_visa"),
						rs.getString("pagamento_master"), rs.getString("pagamento_cielo"),
						rs.getString("pagamento_hiper"), rs.getString("pagamento_outro"), rs.getString("descricao"),
						rs.getString("imagem"), rs.getString("user"));

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
				
				
				estabelecimento.setEndereco(endereco);
				estabelecimento.setContato(contato);
				estabelecimento.setUser(user);

				estabelecimentos.add(estabelecimento);

			}

		} finally {
			stmt.close();
			rs.close();
		}

		return estabelecimentos;

	}



}
