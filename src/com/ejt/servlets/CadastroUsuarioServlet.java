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
import com.ejt.usuario.SenhaInvalidaException;
import com.ejt.usuario.Usuario;
import com.ejt.usuario.UsuarioJaCadastradoException;
import com.ejt.util.JavaMD5Hash;
import com.sun.xml.internal.messaging.saaj.soap.FastInfosetDataContentHandler;

/**
 * Servlet implementation class CadastroUsuarioServelt
 */
@WebServlet("/CadastroUsuarioServelt")
public class CadastroUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastroUsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//		// TODO Auto-generated method stub
//		//response.getWriter().append("Served at: ").append(request.getContextPath());
//		String nome = request.getParameter("nome");
//		String senha = request.getParameter("senha");
//		String email = request.getParameter("email");
//		
//		System.out.println("Nome: " + nome);
//		System.out.println("Senha: " +senha);
//		System.out.println("Email: " + email);
//		
//		Usuario usuario = new Usuario(nome, email, senha);
//		
//		Fachada fachada = Fachada.getInstance();
//		
//		try {
//			fachada.usuarioCadastrar(usuario);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		String nome = request.getParameter("nome");
		String senha = request.getParameter("senha");
		String email = request.getParameter("email");
		
		Usuario usuario = new Usuario(nome, email, JavaMD5Hash.md5(senha));
		
		Fachada fachada = Fachada.getInstance();
		
		try {
			fachada.usuarioCadastrar(usuario);
			HttpSession sessionLogin = request.getSession();
			sessionLogin.setAttribute("UsuarioLogado", usuario);
			response.sendRedirect("index.jsp");
		}  catch (CampoObrigatorioException e1) {

			System.out.println(e1.getMessage());
			String mensagemErro = e1.getMessage();
			request.setAttribute("messagemErro", mensagemErro);
			RequestDispatcher rd = request.getRequestDispatcher("cadastrousuario.jsp");
			rd.forward(request, response);
			
		} catch (UsuarioJaCadastradoException e1) {

			System.out.println(e1.getMessage());
			String mensagemErro = e1.getMessage();
			request.setAttribute("messagemErro", mensagemErro);
			RequestDispatcher rd = request.getRequestDispatcher("cadastrousuario.jsp");
			rd.forward(request, response);
			
			
		} catch (SenhaInvalidaException e1) {

			System.out.println(e1.getMessage());
			String mensagemErro = e1.getMessage();
			request.setAttribute("messagemErro", mensagemErro);
			RequestDispatcher rd = request.getRequestDispatcher("cadastrousuario.jsp");
			rd.forward(request, response);	
				
			
			
		} catch (IOException e1) {  

			System.out.println(e1.getMessage());
			String mensagemErro = e1.getMessage();
			request.setAttribute("messagemErro", mensagemErro);
			RequestDispatcher rd = request.getRequestDispatcher("cadastrousuario.jsp");
			rd.forward(request, response);
			
	    	
		} catch (ServletException e1) {  

			System.out.println(e1.getMessage());
			String mensagemErro = e1.getMessage();
			request.setAttribute("messagemErro", mensagemErro);
			RequestDispatcher rd = request.getRequestDispatcher("cadastrousuario.jsp");
			rd.forward(request, response);
			
	    	
		} catch (Exception e1) {

			System.out.println(e1.getMessage());
			String mensagemErro = e1.getMessage();
			request.setAttribute("messagemErro", mensagemErro);
			RequestDispatcher rd = request.getRequestDispatcher("cadastrousuario.jsp");
			rd.forward(request, response);
			
		}
	
	}

}
