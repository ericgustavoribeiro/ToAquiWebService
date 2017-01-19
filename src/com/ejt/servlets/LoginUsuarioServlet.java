package com.ejt.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ejt.adm.Adm;
import com.ejt.fachada.Fachada;
import com.ejt.usuario.CampoObrigatorioException;
import com.ejt.usuario.SenhaInvalidaException;
import com.ejt.usuario.Usuario;
import com.ejt.usuario.UsuarioNaoEncontradoException;
import com.ejt.util.JavaMD5Hash;

/**
 * Servlet implementation class LoginUsuarioServlet
 */
@WebServlet("/LoginUsuarioServlet")
public class LoginUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginUsuarioServlet() {
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
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		
		Fachada fachada = Fachada.getInstance();
		
//		try {
//			if (fachada.admLogin(email, senha) == true) {
//				System.out.println("ADM LOGADO");
//				Adm adm = fachada.admProcurarEmail(email);
//
//				HttpSession sessionLoginAdm = request.getSession();
//				sessionLoginAdm.setAttribute("AdmLogado", adm);
//
//				response.sendRedirect("index.jsp");
//			} else {
//				throw new com.ejt.adm.SenhaInvalidaException();
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		try {
			
			if(fachada.usuarioLogin(email, JavaMD5Hash.md5(senha)) == true){
			Usuario usuario = fachada.usuarioProcurarEmail(email);
			HttpSession sessionLogin = request.getSession();
			sessionLogin.setAttribute("UsuarioLogado", usuario);
			response.sendRedirect("index.jsp");
			} else if(fachada.admLogin(email, senha) == true){
			Adm adm = fachada.admProcurarEmail(email);
			HttpSession sessionLoginAdm = request.getSession();
			sessionLoginAdm.setAttribute("AdmLogado", adm);	
			response.sendRedirect("index.jsp");	
			}else{
				throw new SenhaInvalidaException();
			}

			
		} catch (CampoObrigatorioException e1) {
			
			String mensagemErro = e1.getMessage();
			request.setAttribute("messagemErro", mensagemErro);
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);	
			
		}  catch (SenhaInvalidaException e1) {
			
			String mensagemErro = e1.getMessage();
			request.setAttribute("messagemErro", mensagemErro);
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);	
			
			
			
		} catch (UsuarioNaoEncontradoException e1) {
			
			String mensagemErro = e1.getMessage();
			request.setAttribute("messagemErro", mensagemErro);
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);	
			
			
			
		} catch (IOException e1) {  
			
			String mensagemErro = e1.getMessage();
			request.setAttribute("messagemErro", mensagemErro);
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);	
			
	    	
		} catch (ServletException e1) {  

			String mensagemErro = e1.getMessage();
			request.setAttribute("messagemErro", mensagemErro);
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);	
			
	    	
		} catch (Exception e1) {
			
			String mensagemErro = "OPS ! ALGO ERRADO TENTE NOVAMENTE";
			request.setAttribute("messagemErro", mensagemErro);
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);	
			
		}
	}

}
