package com.ejt.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ejt.fachada.Fachada;
import com.ejt.usuario.CampoObrigatorioException;
import com.ejt.usuario.UsuarioNaoEncontradoException;

/**
 * Servlet implementation class RemoverUsuarioServlet
 */
@WebServlet("/RemoverUsuarioServlet")
public class RemoverUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoverUsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Fachada fachada = Fachada.getInstance();
		int id = Integer.parseInt(request.getParameter("id_user"));
		
		try {
			fachada.usuarioRemoverId(id);
			
			HttpSession sessionLogin = request.getSession(false);

			if(sessionLogin != null){
				sessionLogin.invalidate();
				response.sendRedirect("index.jsp");
			}
		}  catch (CampoObrigatorioException e1) {

			System.out.println(e1.getMessage());
			String mensagemErro = e1.getMessage();
			request.setAttribute("messagemErro", mensagemErro);
			RequestDispatcher rd = request.getRequestDispatcher("perfil.jsp");
			rd.forward(request, response);
			
		} catch (UsuarioNaoEncontradoException e1) {

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
