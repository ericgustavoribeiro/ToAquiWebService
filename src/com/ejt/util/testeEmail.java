package com.ejt.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class testeEmail {

	public static void main(String[] args) {
		

		 String to = "marcioandredasilvaalmeida@gmail.com";//change accordingly  
		 String from = "sistemanaoresponder@gmail.com";
		 String subject = "Seu cart�o de Natal :D :D :D";
		 String messageText = "<html><a href=\"http://www.foldersecartoes.com.br/cartoes_virtuais.htm\"><img src=\"http://www.foldersecartoes.com.br/webcards2016/natal006.gif\" border=\"0\" alt=\"Anivers�rio\"></a><br><a href=\"http://www.foldersecartoes.com.br/cartoes_virtuais.htm\"><b>Clique aqui para mais Cart�es de Natal No Folders&Cart�es</b></a><br><br></html>";
		  
		  //Get the session object  
		  Properties props = new Properties();  
		  props.put("mail.smtp.host", "smtp.gmail.com");  
		  props.put("mail.smtp.socketFactory.port", "465");  
		  props.put("mail.smtp.socketFactory.class",  
		            "javax.net.ssl.SSLSocketFactory");  
		  props.put("mail.smtp.auth", "true");  
		  props.put("mail.smtp.port", "465");  
		   
		  Session session = Session.getDefaultInstance(props,  
		   new javax.mail.Authenticator() {  
			  protected PasswordAuthentication getPasswordAuthentication() {  
				  return new PasswordAuthentication("mauriciomanoel079@gmail.com","masakasa");//change accordingly  
			  }  
		  });  
		   
		  //compose message  
		  try {  
		   MimeMessage message = new MimeMessage(session);  
		   message.setFrom(new InternetAddress(from));//change accordingly  
		   message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
		   message.setSubject(subject);  
		   message.setContent(messageText, "text/html; charset=utf-8");
		   //message.setText(messageText);  
		     
		   //send message  
		   Transport.send(message);  
		  
		   System.out.println("message sent successfully");  
		   
		  }catch (MessagingException e) {
			  throw new RuntimeException(e);
		  }
	}
}
