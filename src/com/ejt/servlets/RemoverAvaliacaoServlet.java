package com.ejt.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ejt.estabelecimento.Estabelecimento;
import com.ejt.fachada.Fachada;

/**
 * Servlet implementation class RemoverAvaliacaoServlet
 */
@WebServlet("/RemoverAvaliacaoServlet")
public class RemoverAvaliacaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoverAvaliacaoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Fachada fachada = Fachada.getInstance();

		int id_estab = Integer.parseInt(request.getParameter("id_estabelecimento2"));
		int id_user = Integer.parseInt(request.getParameter("id_user2"));
		
		try {
			fachada.avaliacaoRemover(id_user, id_estab);
			Estabelecimento estab = fachada.estabelecimentoProcurarId(id_estab);
			request.setAttribute("nome", estab.getNome());
			RequestDispatcher rd = request.getRequestDispatcher("resultadobuscaestabelecimento.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
