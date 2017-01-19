package com.ejt.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ejt.avaliacao.Avaliacao;
import com.ejt.avaliacao.AvaliacaoJaCadastradaException;
import com.ejt.avaliacao.CampoObrigatorioException;
import com.ejt.estabelecimento.Estabelecimento;
import com.ejt.fachada.Fachada;

/**
 * Servlet implementation class CadastroAvaliacaoServlet
 */
@WebServlet("/CadastroAvaliacaoServlet")
public class CadastroAvaliacaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastroAvaliacaoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
//		int id_user = Integer.parseInt(request.getParameter("id_user"));
//		int id_estabelecimento = Integer.parseInt(request.getParameter("id_estabelecimento"));
//		int nota = Integer.parseInt(request.getParameter("nota"));
//		String descricao = request.getParameter("descricao");
//		System.out.println("ID USER: " + id_user);
//		System.out.println("ID ESTAB: " + id_estabelecimento);
//		System.out.println("NOTA: " + nota);
//		System.out.println("DESCRICAO: " + descricao);
//		
//		Avaliacao avaliacao = new Avaliacao(id_user, id_estabelecimento, nota, descricao);
//		
//		Fachada fachada = Fachada.getInstance();
//		
//		try {
//			fachada.avaliacaoCadastra(avaliacao);
//			response.sendRedirect("index.jsp");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		int id_user = Integer.parseInt(request.getParameter("id_user"));
		int id_estabelecimento = Integer.parseInt(request.getParameter("id_estabelecimento"));
		int nota = Integer.parseInt(request.getParameter("nota"));
		String descricao = request.getParameter("descricao");
		
		
		Avaliacao avaliacao = new Avaliacao(id_user, id_estabelecimento, nota, descricao);
		
		Fachada fachada = Fachada.getInstance();
		
		try {
			fachada.avaliacaoCadastra(avaliacao);
			Estabelecimento estab = fachada.estabelecimentoProcurarId(id_estabelecimento);
			request.setAttribute("nome", estab.getNome());
			RequestDispatcher rd = request.getRequestDispatcher("resultadobuscaestabelecimento.jsp");
			rd.forward(request, response);

			response.sendRedirect("index.jsp");
		} catch (CampoObrigatorioException e1) {

			System.out.println(e1.getMessage());
			String mensagemErro = e1.getMessage();
			request.setAttribute("messagemErro", mensagemErro);
			RequestDispatcher rd = request.getRequestDispatcher("resultadobuscaestabelecimento.jsp");
			rd.forward(request, response);
			
		} catch (AvaliacaoJaCadastradaException e1) {

			System.out.println(e1.getMessage());
			String mensagemErro = e1.getMessage();
			request.setAttribute("messagemErro", mensagemErro);
			RequestDispatcher rd = request.getRequestDispatcher("resultadobuscaestabelecimento.jsp");
			rd.forward(request, response);
			
			
		} catch (IOException e1) {  

			System.out.println(e1.getMessage());
			String mensagemErro = e1.getMessage();
			request.setAttribute("messagemErro", mensagemErro);
			RequestDispatcher rd = request.getRequestDispatcher("resultadobuscaestabelecimento.jsp");
			rd.forward(request, response);
			
	    	
		} catch (ServletException e1) {  

			System.out.println(e1.getMessage());
			String mensagemErro = e1.getMessage();
			request.setAttribute("messagemErro", mensagemErro);
			RequestDispatcher rd = request.getRequestDispatcher("resultadobuscaestabelecimento.jsp");
			rd.forward(request, response);
			
	    	
		} catch (Exception e1) {

			System.out.println(e1.getMessage());
			String mensagemErro = e1.getMessage();
			request.setAttribute("messagemErro", mensagemErro);
			RequestDispatcher rd = request.getRequestDispatcher("resultadobuscaestabelecimento.jsp");
			rd.forward(request, response);
			
		}
	}

}
