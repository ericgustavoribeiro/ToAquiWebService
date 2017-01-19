package com.ejt.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ejt.adm.SenhaInvalidaException;
import com.ejt.fachada.Fachada;
import com.ejt.usuario.CampoObrigatorioException;
import com.ejt.usuario.Usuario;
import com.ejt.usuario.UsuarioNaoEncontradoException;
import com.ejt.util.JavaMD5Hash;

/**
 * Servlet implementation class AlterarUsuarioServlet
 */
@WebServlet("/AlterarUsuarioServlet")
public class AlterarUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlterarUsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		Fachada fachada = Fachada.getInstance();
		
		int id = Integer.parseInt(request.getParameter("id_user"));
		
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		String senha_confir = request.getParameter("senha_confirmation");
		
		if(!(senha.equals(senha_confir))){
			try {
				throw new SenhaInvalidaException();
			} catch (SenhaInvalidaException e1) {
				String mensagemErro = "SENHAS DIFERENTES, VERIFIQUE TENTE NOVAMENTE !";
				request.setAttribute("messagemErro", mensagemErro);
				RequestDispatcher rd = request.getRequestDispatcher("alterarusuario.jsp");
				rd.forward(request, response);
				
			}
		}else{
	
		try {
			Usuario user = fachada.usuarioProcurarId(id);
			user.setNome(nome);
			user.setSenha(JavaMD5Hash.md5(senha));
			
			fachada.usuarioAtualizar(user);
			
			HttpSession sessionLogin = request.getSession(false);

			if(sessionLogin != null){
				sessionLogin.invalidate();
			}
			
			sessionLogin = request.getSession();
			sessionLogin.setAttribute("UsuarioLogado", user);
			
			response.sendRedirect("perfil.jsp");
			
		} catch (CampoObrigatorioException e1) {
			System.out.println("Campo Obrigatorio");
			System.out.println(e1.getMessage());
			String mensagemErro = e1.getMessage();
			request.setAttribute("messagemErro", mensagemErro);
			RequestDispatcher rd = request.getRequestDispatcher("alterarusuario.jsp");
			rd.forward(request, response);
			
		} catch (UsuarioNaoEncontradoException e1) {
			System.out.println("Usuario Não encontrado");
			System.out.println(e1.getMessage());
			String mensagemErro = e1.getMessage();
			request.setAttribute("messagemErro", mensagemErro);
			RequestDispatcher rd = request.getRequestDispatcher("alterarusuario.jsp");
			rd.forward(request, response);
			
			
		} catch (IOException e1) {  
			System.out.println("IO");
			System.out.println(e1.getMessage());
			String mensagemErro = e1.getMessage();
			request.setAttribute("messagemErro", mensagemErro);
			RequestDispatcher rd = request.getRequestDispatcher("alterarusuario.jsp");
			rd.forward(request, response);
			
	    	
		} catch (ServletException e1) {  
			System.out.println("Servlet");
			System.out.println(e1.getMessage());
			String mensagemErro = e1.getMessage();
			request.setAttribute("messagemErro", mensagemErro);
			RequestDispatcher rd = request.getRequestDispatcher("alterarusuario.jsp");
			rd.forward(request, response);
			
	    	
		} catch (Exception e1) {
			System.out.println("Resto");
			System.out.println(e1.getMessage());
			String mensagemErro = e1.getMessage();
			request.setAttribute("messagemErro", mensagemErro);
			RequestDispatcher rd = request.getRequestDispatcher("alterarusuario.jsp");
			rd.forward(request, response);
			
		}
	}
	}
}
