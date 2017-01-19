package com.ejt.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ejt.evento.CampoObrigatorioException;
import com.ejt.evento.EventoNaoEncontradoException;
import com.ejt.fachada.Fachada;

/**
 * Servlet implementation class RemoverEvento
 */
@WebServlet("/RemoverEventoServlet")
public class RemoverEventoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoverEventoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		

		Fachada fachada = Fachada.getInstance();
		int id = Integer.parseInt(request.getParameter("id_evento"));
		
		try {
			fachada.eventoRemoverId(id);
			response.sendRedirect("perfil.jsp");
		} catch (CampoObrigatorioException e1) {

			System.out.println(e1.getMessage());
			String mensagemErro = e1.getMessage();
			request.setAttribute("messagemErro", mensagemErro);
			RequestDispatcher rd = request.getRequestDispatcher("RemoverEventoServlet");
			rd.forward(request, response);
			
		} catch (EventoNaoEncontradoException e1) {

			System.out.println(e1.getMessage());
			String mensagemErro = e1.getMessage();
			request.setAttribute("messagemErro", mensagemErro);
			RequestDispatcher rd = request.getRequestDispatcher("RemoverEventoServlet");
			rd.forward(request, response);
			
		
			
			
		} catch (IOException e1) {  

			System.out.println(e1.getMessage());
			String mensagemErro = e1.getMessage();
			request.setAttribute("messagemErro", mensagemErro);
			RequestDispatcher rd = request.getRequestDispatcher("RemoverEventoServlet");
			rd.forward(request, response);
			
	    	
		} catch (ServletException e1) {  

			System.out.println(e1.getMessage());
			String mensagemErro = e1.getMessage();
			request.setAttribute("messagemErro", mensagemErro);
			RequestDispatcher rd = request.getRequestDispatcher("RemoverEventoServlet");
			rd.forward(request, response);
			
	    	
		} catch (Exception e1) {

			System.out.println(e1.getMessage());
			String mensagemErro = e1.getMessage();
			request.setAttribute("messagemErro", mensagemErro);
			RequestDispatcher rd = request.getRequestDispatcher("RemoverEventoServlet");
			rd.forward(request, response);
			
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		
		Fachada fachada = Fachada.getInstance();
		int id = Integer.parseInt(request.getParameter("id_evento"));
		
		try {
			fachada.eventoRemoverId(id);
			response.sendRedirect("index.jsp");
		} catch (CampoObrigatorioException e1) {

			System.out.println(e1.getMessage());
			String mensagemErro = e1.getMessage();
			request.setAttribute("messagemErro", mensagemErro);
			RequestDispatcher rd = request.getRequestDispatcher("perfil.jsp");
			rd.forward(request, response);
			
		} catch (EventoNaoEncontradoException e1) {

			System.out.println(e1.getMessage());
			String mensagemErro = e1.getMessage();
			request.setAttribute("messagemErro", mensagemErro);
			RequestDispatcher rd = request.getRequestDispatcher("perfil.jsp");
			rd.forward(request, response);
			
		
			
			
		} catch (IOException e1) {  

			System.out.println(e1.getMessage());
			String mensagemErro = e1.getMessage();
			request.setAttribute("messagemErro", mensagemErro);
			RequestDispatcher rd = request.getRequestDispatcher("perfil.jsp");
			rd.forward(request, response);
			
	    	
		} catch (ServletException e1) {  

			System.out.println(e1.getMessage());
			String mensagemErro = e1.getMessage();
			request.setAttribute("messagemErro", mensagemErro);
			RequestDispatcher rd = request.getRequestDispatcher("perfil.jsp");
			rd.forward(request, response);
			
	    	
		} catch (Exception e1) {

			System.out.println(e1.getMessage());
			String mensagemErro = e1.getMessage();
			request.setAttribute("messagemErro", mensagemErro);
			RequestDispatcher rd = request.getRequestDispatcher("perfil.jsp");
			rd.forward(request, response);
			
		}
		
		
	}

}
