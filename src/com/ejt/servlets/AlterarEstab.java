package com.ejt.servlets;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ejt.contato.Contato;
import com.ejt.endereco.Endereco;
import com.ejt.estabelecimento.CampoObrigatorioException;
import com.ejt.estabelecimento.Estabelecimento;
import com.ejt.estabelecimento.EstabelecimentoNaoEncontradoException;
import com.ejt.fachada.Fachada;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class AlterarEstab
 */
@WebServlet("/AlterarEstab")
public class AlterarEstab extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SAVE_DIR = "imagens";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AlterarEstab() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

		response.setContentType("text/jps;charset=UTF-8");

		
		MultipartRequest multipartRequest = new MultipartRequest(request,
				getServletContext().getRealPath("/") + File.separator + "imgEstabelecimento\\",
				/* 150MB */ 153600 * 153600, new DefaultFileRenamePolicy());

		
		if (multipartRequest.getParameter("enviar") != null && multipartRequest.getFile("imagem") != null) {
			upload(request, response, multipartRequest);
		}else if(multipartRequest.getParameter("enviar") != null &&  multipartRequest.getFile("imagem") == null){
			Fachada fachada = Fachada.getInstance();

			int id = Integer.parseInt(multipartRequest.getParameter("id_estabelecimento"));

			String imagem = multipartRequest.getParameter("img");
			String nome = multipartRequest.getParameter("nome");
			String logradouro = multipartRequest.getParameter("logradouro");
			String numero = multipartRequest.getParameter("numero");
			String bairro = multipartRequest.getParameter("bairro");
			String email = multipartRequest.getParameter("email");
			String telefone = multipartRequest.getParameter("telefone").replace("(", "").replace(")", "")
					.replace("-", "").replace(" ", "");
			String celular = multipartRequest.getParameter("celular").replace("(", "").replace(")", "")
					.replace("-", "").replace(" ", "");
			String categoria = multipartRequest.getParameter("categoria");
			String descricao = multipartRequest.getParameter("descricao");
			String pagamento_visa = multipartRequest.getParameter("pagamento_visa");
			if (pagamento_visa == null) {
				pagamento_visa = "Indisponivel";
			}
			String pagamento_master = multipartRequest.getParameter("pagamento_master");
			if (pagamento_master == null) {
				pagamento_master = "Indisponivel";
			}
			String pagamento_cielo = multipartRequest.getParameter("pagamento_cielo");
			if (pagamento_cielo == null) {
				pagamento_cielo = "Indisponivel";
			}
			String pagamento_hiper = multipartRequest.getParameter("pagamento_hiper");
			if (pagamento_hiper == null) {
				pagamento_hiper = "Indisponivel";
			}
			String pagamento_outro = multipartRequest.getParameter("pagamento_outro");
			if (pagamento_outro == null) {
				pagamento_outro = "Indisponivel";
			}
			
			try{
			Endereco endereco = new Endereco(logradouro, numero, bairro);
			Contato contato = new Contato(email, telefone, celular);
			
			Estabelecimento estab = fachada.estabelecimentoProcurarId(id);
			estab.setNome(nome);
			estab.setCategoria(categoria);
			estab.setImagem(imagem);
			estab.setDescricao(descricao);
			estab.setContato(contato);
			estab.setEndereco(endereco);
			estab.setPagamento_cielo(pagamento_cielo);
			estab.setPagamento_hiper(pagamento_hiper);
			estab.setPagamento_master(pagamento_master);
			estab.setPagamento_outro(pagamento_outro);
			estab.setPagamento_visa(pagamento_visa);
			fachada.estabelecimentoAtualizar(estab);

			response.sendRedirect("perfil.jsp");
		} catch (CampoObrigatorioException e1) {
			
			System.out.println(e1.getMessage());
			String mensagemErro = e1.getMessage();
			request.setAttribute("messagemErro", mensagemErro);
			RequestDispatcher rd = request.getRequestDispatcher("alterarestabelecimento.jsp");
			rd.forward(request, response);
			
		
		} catch (EstabelecimentoNaoEncontradoException e1) {
			System.out.println(e1.getMessage());
			String mensagemErro = e1.getMessage();
			request.setAttribute("messagemErro", mensagemErro);
			RequestDispatcher rd = request.getRequestDispatcher("alterarestabelecimento.jsp");
			rd.forward(request, response);
			
		}  catch (IOException e1) {  
			System.out.println(e1.getMessage());
			String mensagemErro = e1.getMessage();
			request.setAttribute("messagemErro", mensagemErro);
			RequestDispatcher rd = request.getRequestDispatcher("alterarestabelecimento.jsp");
			rd.forward(request, response);
			
	    	
		} catch (ServletException e1) {  
			System.out.println(e1.getMessage());
			String mensagemErro = e1.getMessage();
			request.setAttribute("messagemErro", mensagemErro);
			RequestDispatcher rd = request.getRequestDispatcher("alterarestabelecimento.jsp");
			rd.forward(request, response);
			
	    	
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
			String mensagemErro = e1.getMessage();
			request.setAttribute("messagemErro", mensagemErro);
			RequestDispatcher rd = request.getRequestDispatcher("alterarestabelecimento.jsp");
			rd.forward(request, response);
			
	
		}

		}
		
	}

	protected void upload(HttpServletRequest request, HttpServletResponse response, MultipartRequest multipartRequest)
			throws ServletException, IOException {

		response.setContentType("text/jps;charset=UTF-8");

		try {

			String dirName = request.getServletContext().getRealPath("/img/imgupload");

			if (multipartRequest.getParameter("enviar") != null) {
				// cadastro(request, response, multipartRequest);

				Fachada fachada = Fachada.getInstance();

				int id = Integer.parseInt(multipartRequest.getParameter("id_estabelecimento"));

				String nome = multipartRequest.getParameter("nome");
				String logradouro = multipartRequest.getParameter("logradouro");
				String numero = multipartRequest.getParameter("numero");
				String bairro = multipartRequest.getParameter("bairro");
				String email = multipartRequest.getParameter("email");
				String telefone = multipartRequest.getParameter("telefone").replace("(", "").replace(")", "")
						.replace("-", "").replace(" ", "");
				String celular = multipartRequest.getParameter("celular").replace("(", "").replace(")", "")
						.replace("-", "").replace(" ", "");
				String categoria = multipartRequest.getParameter("categoria");
				File tmpFile = multipartRequest.getFile("imagem");
				String descricao = multipartRequest.getParameter("descricao");
				String pagamento_visa = multipartRequest.getParameter("pagamento_visa");
				if (pagamento_visa == null) {
					pagamento_visa = "Indisponivel";
				}
				String pagamento_master = multipartRequest.getParameter("pagamento_master");
				if (pagamento_master == null) {
					pagamento_master = "Indisponivel";
				}
				String pagamento_cielo = multipartRequest.getParameter("pagamento_cielo");
				if (pagamento_cielo == null) {
					pagamento_cielo = "Indisponivel";
				}
				String pagamento_hiper = multipartRequest.getParameter("pagamento_hiper");
				if (pagamento_hiper == null) {
					pagamento_hiper = "Indisponivel";
				}
				String pagamento_outro = multipartRequest.getParameter("pagamento_outro");
				if (pagamento_outro == null) {
					pagamento_outro = "Indisponivel";
				}

				Endereco endereco = new Endereco(logradouro, numero, bairro);
				Contato contato = new Contato(email, telefone, celular);

				String appPath = request.getServletContext().getRealPath("");

				String savePath = appPath + File.separator + SAVE_DIR;

				// String caminho = "C:\\Users\\Eric\\Desktop\\FileUpload";

				BufferedImage imagem = ImageIO.read(tmpFile);

				ImageIO.write(imagem, "jpg", new File(savePath));
				String imagem2 = "imgEstabelecimento/" + tmpFile.getName();

				// ImageIO.write(imagem, "jpg", new File(caminho +
				// File.separator + (nome +".jpg")));

				try {
					
					Estabelecimento estab = fachada.estabelecimentoProcurarId(id);
					estab.setNome(nome);
					estab.setCategoria(categoria);
					estab.setImagem(imagem2);
					estab.setDescricao(descricao);
					estab.setContato(contato);
					estab.setEndereco(endereco);
					estab.setPagamento_cielo(pagamento_cielo);
					estab.setPagamento_hiper(pagamento_hiper);
					estab.setPagamento_master(pagamento_master);
					estab.setPagamento_outro(pagamento_outro);
					estab.setPagamento_visa(pagamento_visa);
					fachada.estabelecimentoAtualizar(estab);

					response.sendRedirect("perfil.jsp");
				}catch (CampoObrigatorioException e1) {
					
					System.out.println(e1.getMessage());
					String mensagemErro = e1.getMessage();
					request.setAttribute("messagemErro", mensagemErro);
					RequestDispatcher rd = request.getRequestDispatcher("alterarestabelecimento.jsp");
					rd.forward(request, response);
					
				
				} catch (EstabelecimentoNaoEncontradoException e1) {
					System.out.println(e1.getMessage());
					String mensagemErro = e1.getMessage();
					request.setAttribute("messagemErro", mensagemErro);
					RequestDispatcher rd = request.getRequestDispatcher("alterarestabelecimento.jsp");
					rd.forward(request, response);
					
				}  catch (IOException e1) {  
					System.out.println(e1.getMessage());
					String mensagemErro = e1.getMessage();
					request.setAttribute("messagemErro", mensagemErro);
					RequestDispatcher rd = request.getRequestDispatcher("alterarestabelecimento.jsp");
					rd.forward(request, response);
					
			    	
				} catch (ServletException e1) {  
					System.out.println(e1.getMessage());
					String mensagemErro = e1.getMessage();
					request.setAttribute("messagemErro", mensagemErro);
					RequestDispatcher rd = request.getRequestDispatcher("alterarestabelecimento.jsp");
					rd.forward(request, response);
					
			    	
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
					String mensagemErro = e1.getMessage();
					request.setAttribute("messagemErro", mensagemErro);
					RequestDispatcher rd = request.getRequestDispatcher("alterarestabelecimento.jsp");
					rd.forward(request, response);
					
			
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
