package com.ejt.servlets;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ejt.contato.Contato;
import com.ejt.endereco.Endereco;
import com.ejt.estabelecimento.Estabelecimento;
import com.ejt.evento.CampoObrigatorioException;
import com.ejt.evento.Evento;
import com.ejt.evento.EventoJaCadastradoException;
import com.ejt.fachada.Fachada;
import com.ejt.usuario.Usuario;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class CadastroEvento
 */
@WebServlet("/CadastroEvento")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
maxFileSize=1024*1024*10,      // 10MB
maxRequestSize=1024*1024*50) // 20MB)
public class CadastroEvento extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SAVE_DIR = "imagens";
   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastroEvento() {
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
		response.setContentType("text/jps;charset=UTF-8");
		
		try {  
            MultipartRequest multipartRequest = new MultipartRequest(request, getServletContext().getRealPath("/") +
            		File.separator + "imgEvento\\", /* 150MB */ 153600 * 153600, new DefaultFileRenamePolicy());  
            
            if(multipartRequest.getParameter("enviar") != null){
            	String dirName = request.getServletContext().getRealPath("/img/imgupload");  
                
                
                
                if (multipartRequest.getFile("imagem") != null) {  
                //	cadastro(request, response, multipartRequest);  
                
                	File tmpFile = multipartRequest.getFile("imagem");
                	String nome = multipartRequest.getParameter("nome");
            		String local = multipartRequest.getParameter("local");
            		String logradouro = multipartRequest.getParameter("logradouro");
            		String numero = multipartRequest.getParameter("numero");
            		String bairro = multipartRequest.getParameter("bairro");
            		String email = multipartRequest.getParameter("email");
            		String telefone = multipartRequest.getParameter("telefone").replace("(", "").replace(")", "").replace("-", "").replace(" ", "");
            		String celular = multipartRequest.getParameter("celular").replace("(", "").replace(")", "").replace("-", "").replace(" ", "");
            
            		
            		
            		float valor_ingresso = Float.parseFloat(multipartRequest.getParameter("valor_ingresso"));
            		String data_evento = multipartRequest.getParameter("data_evento");
            		String descricao = multipartRequest.getParameter("descricao");
            		
            		
            		HttpSession sessionLogin = request.getSession();
            		Usuario usuario = (Usuario)sessionLogin.getAttribute("UsuarioLogado");
            		
            		Endereco endereco = new Endereco(logradouro, numero, bairro);
            		Contato contato = new Contato(email, telefone, celular);
                    String appPath = request.getServletContext().getRealPath("");
                    
                   
                    String savePath = appPath + File.separator + SAVE_DIR;
            	
//                    String caminho = "C:\\Users\\Eric\\Desktop\\FileUpload";
//            		
                    
            		 BufferedImage imagem = ImageIO.read(tmpFile);
//            	     ImageIO.write(imagem, "jpg", new File(caminho + File.separator + (nome +".jpg")));
            		 
//            		 file:///C:/Users/Juliano%20Araujo/Dropbox/Archive/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp2/wtpwebapps/ToAqui/imgEstabelecimento/chipjunior.JPG
//            			 c:\Users\Juliano%20Araujo\Dropbox\Archive\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp2\wtpwebapps\ToAqui\imgEstabelecimento\chipjunior.JPG
//            			 
            	     ImageIO.write(imagem, "jpg", new File(savePath));
//            	     
//            	     System.out.println(tmpFile.getName());
//            	     System.out.println(imagem);
            	    String imagem2 = "imgEvento/" + tmpFile.getName();
            	    
//            	    System.out.println(tmpFile.getPath());
            	     
            	    Evento evento = new Evento(nome, local, endereco, contato, data_evento, valor_ingresso, imagem2, descricao, usuario.getId_user());
            		
            	    
            		Fachada fachada = Fachada.getInstance();
            		
            		try {
            			fachada.eventoCadastrar(evento);
            			response.sendRedirect("perfil.jsp");
            		}catch (CampoObrigatorioException e1) {

            			System.out.println(e1.getMessage());
            			String mensagemErro = e1.getMessage();
            			request.setAttribute("messagemErro", mensagemErro);
            			RequestDispatcher rd = request.getRequestDispatcher("CadastroEvento");
            			rd.forward(request, response);
            			
            		} catch (EventoJaCadastradoException e1) {

            			System.out.println(e1.getMessage());
            			String mensagemErro = e1.getMessage();
            			request.setAttribute("messagemErro", mensagemErro);
            			RequestDispatcher rd = request.getRequestDispatcher("CadastroEvento");
            			rd.forward(request, response);
            			
            			
            		} catch (IOException e1) {  

            			System.out.println(e1.getMessage());
            			String mensagemErro = e1.getMessage();
            			request.setAttribute("messagemErro", mensagemErro);
            			RequestDispatcher rd = request.getRequestDispatcher("CadastroEvento");
            			rd.forward(request, response);
            			
            	    	
            		} catch (ServletException e1) {  

            			System.out.println(e1.getMessage());
            			String mensagemErro = e1.getMessage();
            			request.setAttribute("messagemErro", mensagemErro);
            			RequestDispatcher rd = request.getRequestDispatcher("CadastroEvento");
            			rd.forward(request, response);
            			
            	    	
            		} catch (Exception e1) {

            			System.out.println(e1.getMessage());
            			String mensagemErro = e1.getMessage();
            			request.setAttribute("messagemErro", mensagemErro);
            			RequestDispatcher rd = request.getRequestDispatcher("CadastroEvento");
            			rd.forward(request, response);
            			
            		} 		
                }else if (multipartRequest.getFile("imagem") == null){
                	
                	String nome = multipartRequest.getParameter("nome");
            		String local = multipartRequest.getParameter("local");
            		String logradouro = multipartRequest.getParameter("logradouro");
            		String numero = multipartRequest.getParameter("numero");
            		String bairro = multipartRequest.getParameter("bairro");
            		String email = multipartRequest.getParameter("email");
            		String telefone = multipartRequest.getParameter("telefone").replace("(", "").replace(")", "").replace("-", "").replace(" ", "");
            		String celular = multipartRequest.getParameter("celular").replace("(", "").replace(")", "").replace("-", "").replace(" ", "");
            
            		
            		
            		float valor_ingresso = Float.parseFloat(multipartRequest.getParameter("valor_ingresso"));
            		String data_evento = multipartRequest.getParameter("data_evento");
            		String imagem = "img/semimg.png";

            		String descricao = multipartRequest.getParameter("descricao");
            		
            		
            		Endereco endereco = new Endereco(logradouro, numero, bairro);
            		Contato contato = new Contato(email, telefone, celular);
            		
            		HttpSession sessionLogin = request.getSession();
            		Usuario usuario = (Usuario)sessionLogin.getAttribute("UsuarioLogado");
            		
            		
            		Fachada fachada = Fachada.getInstance();
            		
            		
            		Evento evento = new Evento(nome, local, endereco, contato, data_evento, valor_ingresso,
            				imagem, descricao, usuario.getId_user());
            		
            		try {
            			fachada.eventoCadastrar(evento);
            			response.sendRedirect("perfil.jsp");
            		} catch (CampoObrigatorioException e1) {

            			System.out.println(e1.getMessage());
            			String mensagemErro = e1.getMessage();
            			request.setAttribute("messagemErro", mensagemErro);
            			RequestDispatcher rd = request.getRequestDispatcher("cadastroevento.jsp");
            			rd.forward(request, response);
            			
            		} catch (EventoJaCadastradoException e1) {

            			System.out.println(e1.getMessage());
            			String mensagemErro = e1.getMessage();
            			request.setAttribute("messagemErro", mensagemErro);
            			RequestDispatcher rd = request.getRequestDispatcher("cadastroevento.jsp");
            			rd.forward(request, response);
            			
            			
            		} catch (IOException e1) {  

            			System.out.println(e1.getMessage());
            			String mensagemErro = e1.getMessage();
            			request.setAttribute("messagemErro", mensagemErro);
            			RequestDispatcher rd = request.getRequestDispatcher("cadastroevento.jsp");
            			rd.forward(request, response);
            			
            	    	
            		} catch (ServletException e1) {  

            			System.out.println(e1.getMessage());
            			String mensagemErro = e1.getMessage();
            			request.setAttribute("messagemErro", mensagemErro);
            			RequestDispatcher rd = request.getRequestDispatcher("cadastroevento.jsp");
            			rd.forward(request, response);
            			
            			
            		} catch (NumberFormatException e1) {

            			System.out.println(e1.getMessage());
            			String mensagemErro = "OPS ! ALGO OCORREU ERRADO TENTE NOVAMENTE !";
            			request.setAttribute("messagemErro", mensagemErro);
            			RequestDispatcher rd = request.getRequestDispatcher("cadastroevento.jsp");
            			rd.forward(request, response);
            			
            		} catch (Exception e1) {

            			System.out.println(e1.getMessage());
            			String mensagemErro = e1.getMessage();
            			request.setAttribute("messagemErro", mensagemErro);
            			RequestDispatcher rd = request.getRequestDispatcher("cadastroevento.jsp");
            			rd.forward(request, response);
            			
            		}
                } 
            }  
        } catch (IOException ex) {  
        	ex.printStackTrace();
        }
	}

}
