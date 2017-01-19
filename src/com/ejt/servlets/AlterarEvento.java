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
import javax.servlet.http.HttpSession;

import com.ejt.contato.Contato;
import com.ejt.endereco.Endereco;
import com.ejt.evento.CampoObrigatorioException;
import com.ejt.evento.Evento;
import com.ejt.evento.EventoNaoEncontradoException;
import com.ejt.fachada.Fachada;
import com.ejt.usuario.Usuario;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class AlterarEvento
 */
@WebServlet("/AlterarEvento")
public class AlterarEvento extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SAVE_DIR = "imagens";
   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlterarEvento() {
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

MultipartRequest multipartRequest = new MultipartRequest(request, getServletContext().getRealPath("/") +
		File.separator + "imgEvento\\", /* 150MB */ 153600 * 153600, new DefaultFileRenamePolicy());  


if (multipartRequest.getParameter("enviar") != null && multipartRequest.getFile("imagem") != null) {
	upload(request, response, multipartRequest);
	
}else if(multipartRequest.getParameter("enviar") != null && multipartRequest.getFile("imagem") == null){
	
	int id = Integer.parseInt(multipartRequest.getParameter("id_evento"));
	
	String imagem = multipartRequest.getParameter("img");
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
	
	try {
		Fachada fachada = Fachada.getInstance();
    	
		Evento evento = fachada.eventoProcurarId(id);
		evento.setNome(nome);
		evento.setLocal(local);
		evento.setContato(contato);
		evento.setEndereco(endereco);
		evento.setImagem(imagem);
		evento.setDescricao(descricao);
		//evento.setData_evento(data_evento);
		evento.setValor_ingresso(valor_ingresso);
		fachada.eventoAtualizar(evento);
		
		response.sendRedirect("perfil.jsp");
	} catch (CampoObrigatorioException e1) {
		
		System.out.println(e1.getMessage());
		String mensagemErro = e1.getMessage();
		request.setAttribute("messagemErro", mensagemErro);
		RequestDispatcher rd = request.getRequestDispatcher("AlterarEventoServlet");
		rd.forward(request, response);
		
	} catch (EventoNaoEncontradoException e1) {

		System.out.println(e1.getMessage());
		String mensagemErro = e1.getMessage();
		request.setAttribute("messagemErro", mensagemErro);
		RequestDispatcher rd = request.getRequestDispatcher("AlterarEventoServlet");
		rd.forward(request, response);
		
		
	}  catch (IOException e1) {  

		System.out.println(e1.getMessage());
		String mensagemErro = e1.getMessage();
		request.setAttribute("messagemErro", mensagemErro);
		RequestDispatcher rd = request.getRequestDispatcher("AlterarEventoServlet");
		rd.forward(request, response);
    	
	} catch (ServletException e1) {  

		System.out.println(e1.getMessage());
		String mensagemErro = e1.getMessage();
		request.setAttribute("messagemErro", mensagemErro);
		RequestDispatcher rd = request.getRequestDispatcher("AlterarEventoServlet");
		rd.forward(request, response);
    	
	} catch (Exception e1) {

		System.out.println(e1.getMessage());
		String mensagemErro = e1.getMessage();
		request.setAttribute("messagemErro", mensagemErro);
		RequestDispatcher rd = request.getRequestDispatcher("AlterarEventoServlet");
		rd.forward(request, response);
		
	}
	
}
		
	}

	
	protected void upload(HttpServletRequest request, HttpServletResponse response, MultipartRequest multipartRequest) 
			throws ServletException, IOException {
		try {  
           
            
            
            String dirName = request.getServletContext().getRealPath("/img/imgupload");  
            
            
            //System.out.println(dirName);
    //        System.out.println(multipartRequest.getParameter("imagem"));
            
            if (multipartRequest.getParameter("enviar") != null) {  
            //	cadastro(request, response, multipartRequest);  
            
            	Fachada fachada = Fachada.getInstance();
            	
            	int id = Integer.parseInt(multipartRequest.getParameter("id_evento"));
            	
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
        	
//                String caminho = "C:\\Users\\Eric\\Desktop\\FileUpload";
//        		
                
        		 BufferedImage imagem = ImageIO.read(tmpFile);
//        	     ImageIO.write(imagem, "jpg", new File(caminho + File.separator + (nome +".jpg")));
        		 
//        		 file:///C:/Users/Juliano%20Araujo/Dropbox/Archive/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp2/wtpwebapps/ToAqui/imgEstabelecimento/chipjunior.JPG
//        			 c:\Users\Juliano%20Araujo\Dropbox\Archive\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp2\wtpwebapps\ToAqui\imgEstabelecimento\chipjunior.JPG
//        			 
        	     ImageIO.write(imagem, "jpg", new File(savePath));
//        	     
//        	     System.out.println(tmpFile.getName());
//        	     System.out.println(imagem);
        	    String imagem2 = "imgEvento/" + tmpFile.getName();
        	    
//        	    System.out.println(tmpFile.getPath());
        	     
        	    
        	    
        	//	Fachada fachada = Fachada.getInstance();
        		
        		try {
        			
        			Evento evento = fachada.eventoProcurarId(id);
        			evento.setNome(nome);
        			evento.setLocal(local);
        			evento.setContato(contato);
        			evento.setEndereco(endereco);
        			evento.setImagem(imagem2);
        			evento.setDescricao(descricao);
        			//evento.setData_evento(data_evento);
        			evento.setValor_ingresso(valor_ingresso);
        			fachada.eventoAtualizar(evento);
        			
        			response.sendRedirect("perfil.jsp");
        		} catch (CampoObrigatorioException e1) {
        			
        			System.out.println(e1.getMessage());
        			String mensagemErro = e1.getMessage();
        			request.setAttribute("messagemErro", mensagemErro);
        			RequestDispatcher rd = request.getRequestDispatcher("alterarevento.jsp");
        			rd.forward(request, response);
        			
        		} catch (EventoNaoEncontradoException e1) {

        			System.out.println(e1.getMessage());
        			String mensagemErro = e1.getMessage();
        			request.setAttribute("messagemErro", mensagemErro);
        			RequestDispatcher rd = request.getRequestDispatcher("alterarevento.jsp");
        			rd.forward(request, response);
        			
        			
        		}  catch (IOException e1) {  

        			System.out.println(e1.getMessage());
        			String mensagemErro = e1.getMessage();
        			request.setAttribute("messagemErro", mensagemErro);
        			RequestDispatcher rd = request.getRequestDispatcher("alterarevento.jsp");
        			rd.forward(request, response);
        	    	
        		} catch (ServletException e1) {  

        			System.out.println(e1.getMessage());
        			String mensagemErro = e1.getMessage();
        			request.setAttribute("messagemErro", mensagemErro);
        			RequestDispatcher rd = request.getRequestDispatcher("alterarevento.jsp");
        			rd.forward(request, response);
        	    	
        		} catch (Exception e1) {

        			System.out.println(e1.getMessage());
        			String mensagemErro = e1.getMessage();
        			request.setAttribute("messagemErro", mensagemErro);
        			RequestDispatcher rd = request.getRequestDispatcher("alterarevento.jsp");
        			rd.forward(request, response);
        			
        		}
        		
            } else {  
                throw new IOException();  
            }  
        } catch (IOException ex) {  
        	ex.printStackTrace();
        }
	}
}
