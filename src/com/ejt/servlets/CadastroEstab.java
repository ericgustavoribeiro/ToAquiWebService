package com.ejt.servlets;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.Normalizer;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;

import org.apache.commons.fileupload.FileUpload;

import org.apache.commons.fileupload.FileItemFactory;

import org.apache.commons.fileupload.FileItem;

import org.apache.commons.fileupload.FileUploadException;

import com.ejt.contato.Contato;
import com.ejt.endereco.Endereco;
import com.ejt.estabelecimento.CampoObrigatorioException;
import com.ejt.estabelecimento.Estabelecimento;
import com.ejt.estabelecimento.EstabelecimentoJaCadastradoException;
import com.ejt.estabelecimento.EstabelecimentoNaoEncontradoException;
import com.ejt.fachada.Fachada;
import com.ejt.usuario.Usuario;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class CadastroEstab
 */
@WebServlet("/CadastroEstab")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
maxFileSize=1024*1024*10,      // 10MB
maxRequestSize=1024*1024*50) // 20MB)
public class CadastroEstab extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SAVE_DIR = "imagens";

  
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CadastroEstab() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	/**

     * @param item FileItem, representa um arquivo que é enviado pelo formulario

     * MultiPart/Form-data

     * @throws IOException

     * @throws ServletException

     */

   
 
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/jps;charset=UTF-8");
		
		try {  
            MultipartRequest multipartRequest = new MultipartRequest(request, getServletContext().getRealPath("/") +
            		File.separator + "imgEstabelecimento\\", /* 150MB */ 153600 * 153600, new DefaultFileRenamePolicy());  
            
            
            
            
            String dirName = request.getServletContext().getRealPath("/img/imgupload");  
            
            
            //System.out.println(dirName);
            
            if (multipartRequest.getParameter("enviar") != null) {  
            //	cadastro(request, response, multipartRequest);  
            	//CAMINHO DA IMAGEM PADRAO = img/semimg.png
            if(multipartRequest.getFile("imagem") != null && multipartRequest.getParameter("enviar") != null){
            	File tmpFile = multipartRequest.getFile("imagem");
        		String nome = multipartRequest.getParameter("nome");
        		String logradouro = multipartRequest.getParameter("logradouro");
        		String numero = multipartRequest.getParameter("numero");
        		String bairro = multipartRequest.getParameter("bairro");
        		String email = multipartRequest.getParameter("email");
        		String telefone = multipartRequest.getParameter("telefone").replace("(", "").replace(")", "").replace("-", "").replace(" ", "");
        		String celular = multipartRequest.getParameter("celular").replace("(", "").replace(")", "").replace("-", "").replace(" ", "");
        
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
        		
        		HttpSession sessionLogin = request.getSession();
        		Usuario usuario = (Usuario)sessionLogin.getAttribute("UsuarioLogado");
        		
        		Endereco endereco = new Endereco(logradouro, numero, bairro);
        		Contato contato = new Contato(email, telefone, celular);
                String appPath = request.getServletContext().getRealPath("");
                
               
                String savePath = appPath + File.separator + SAVE_DIR;
        	
                
        		 BufferedImage imagem = ImageIO.read(tmpFile);
        		 
        	     ImageIO.write(imagem, "jpg", new File(savePath));
        	     
        	     System.out.println(tmpFile.getName());

        	    String imagem2 = "imgEstabelecimento/" + tmpFile.getName();
        	    
        	     
        	     Estabelecimento estabelecimento = new Estabelecimento(nome, endereco, contato, categoria, pagamento_visa, pagamento_master, pagamento_cielo, pagamento_hiper, pagamento_outro, descricao, imagem2, usuario.getId_user());
        	 	
        	    
        		Fachada fachada = Fachada.getInstance();
        		
        		try {
        			fachada.estabelecimentoCadastrar(estabelecimento);
        			response.sendRedirect("perfil.jsp");
        		} catch (CampoObrigatorioException e1) {
        			
        			System.out.println(e1.getMessage());
        			String mensagemErro = e1.getMessage();
        			request.setAttribute("messagemErro", mensagemErro);
        			RequestDispatcher rd = request.getRequestDispatcher("CadastroEstab");
        			rd.forward(request, response);
        			
        		} catch (EstabelecimentoJaCadastradoException e1) {

        			System.out.println(e1.getMessage());
        			String mensagemErro = e1.getMessage();
        			request.setAttribute("messagemErro", mensagemErro);
        			RequestDispatcher rd = request.getRequestDispatcher("CadastroEstab");
        			rd.forward(request, response);
        			
        			
        		} catch (EstabelecimentoNaoEncontradoException e1) {

        			System.out.println(e1.getMessage());
        			String mensagemErro = e1.getMessage();
        			request.setAttribute("messagemErro", mensagemErro);
        			RequestDispatcher rd = request.getRequestDispatcher("CadastroEstab");
        			rd.forward(request, response);
        			
        		} catch (IOException e1) {  

        			System.out.println(e1.getMessage());
        			String mensagemErro = e1.getMessage();
        			request.setAttribute("messagemErro", mensagemErro);
        			RequestDispatcher rd = request.getRequestDispatcher("CadastroEstab");
        			rd.forward(request, response);
        	    	
        		} catch (ServletException e1) {  

        			System.out.println(e1.getMessage());
        			String mensagemErro = e1.getMessage();
        			request.setAttribute("messagemErro", mensagemErro);
        			RequestDispatcher rd = request.getRequestDispatcher("CadastroEstab");
        			rd.forward(request, response);
        	    	
        		} catch (Exception e1) {

        			System.out.println(e1.getMessage());
        			String mensagemErro = e1.getMessage();
        			request.setAttribute("messagemErro", mensagemErro);
        			RequestDispatcher rd = request.getRequestDispatcher("CadastroEstab");
        			rd.forward(request, response);
        			
        		}
        		
            } else if(multipartRequest.getFile("imagem") == null){
        		
            	String nome = multipartRequest.getParameter("nome");
        		String logradouro = multipartRequest.getParameter("logradouro");
        		String numero = multipartRequest.getParameter("numero");
        		String bairro = multipartRequest.getParameter("bairro");
        		String email = multipartRequest.getParameter("email");
        		String telefone = multipartRequest.getParameter("telefone").replace("(", "").replace(")", "").replace("-", "").replace(" ", "");
        		String celular = multipartRequest.getParameter("celular").replace("(", "").replace(")", "").replace("-", "").replace(" ", "");
        		String categoria = multipartRequest.getParameter("categoria");
        					
        		String imagem = "img/semimg.png";
        	
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
        		
        		HttpSession sessionLogin = request.getSession();
        		Usuario usuario = (Usuario)sessionLogin.getAttribute("UsuarioLogado");
        		
        		Endereco endereco = new Endereco(logradouro, numero, bairro);
        		Contato contato = new Contato(email, telefone, celular);
        		
        		
        		
        	Estabelecimento estabelecimento = new Estabelecimento(nome, endereco, contato, categoria, pagamento_visa, pagamento_master, pagamento_cielo, pagamento_hiper, pagamento_outro, descricao, imagem, usuario.getId_user());
        	
        	Fachada fachada = Fachada.getInstance();
        	
        	try {
        		fachada.estabelecimentoCadastrar(estabelecimento);
        		response.sendRedirect("perfil.jsp");
        	} catch (CampoObrigatorioException e1) {
    			
    			System.out.println(e1.getMessage());
    			String mensagemErro = e1.getMessage();
    			request.setAttribute("messagemErro", mensagemErro);
    			RequestDispatcher rd = request.getRequestDispatcher("cadastroestabelecimento.jsp");
    			rd.forward(request, response);
    			
    		} catch (EstabelecimentoJaCadastradoException e1) {

    			System.out.println(e1.getMessage());
    			String mensagemErro = e1.getMessage();
    			request.setAttribute("messagemErro", mensagemErro);
    			RequestDispatcher rd = request.getRequestDispatcher("cadastroestabelecimento.jsp");
    			rd.forward(request, response);
    			
    			
    		} catch (EstabelecimentoNaoEncontradoException e1) {

    			System.out.println(e1.getMessage());
    			String mensagemErro = e1.getMessage();
    			request.setAttribute("messagemErro", mensagemErro);
    			RequestDispatcher rd = request.getRequestDispatcher("cadastroestabelecimento.jsp");
    			rd.forward(request, response);
    			
    		} catch (IOException e1) {  

    			System.out.println(e1.getMessage());
    			String mensagemErro = e1.getMessage();
    			request.setAttribute("messagemErro", mensagemErro);
    			RequestDispatcher rd = request.getRequestDispatcher("cadastroestabelecimento.jsp");
    			rd.forward(request, response);
    	    	
    		} catch (ServletException e1) {  

    			System.out.println(e1.getMessage());
    			String mensagemErro = e1.getMessage();
    			request.setAttribute("messagemErro", mensagemErro);
    			RequestDispatcher rd = request.getRequestDispatcher("cadastroestabelecimento.jsp");
    			rd.forward(request, response);
    	    	
    		} catch (Exception e1) {

    			System.out.println(e1.getMessage());
    			String mensagemErro = e1.getMessage();
    			request.setAttribute("messagemErro", mensagemErro);
    			RequestDispatcher rd = request.getRequestDispatcher("cadastroestabelecimento.jsp");
    			rd.forward(request, response);
    			
    		}
        	
            }//esse
            
            }	
        } catch (IOException ex) {  
        	ex.printStackTrace();
        }

	}

}
