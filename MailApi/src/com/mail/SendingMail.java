package com.mail;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendingMail {
	public static void main(String[] args) {
		//from mail  username of mail  password-from  to-mail  
		validate("bayyasumanth.7@gmail.com","bayyasumanth.7","B@yya123!@#","sbayya1@ford.com");
	}
	public static boolean validate(String from,final String username,final String password,String to){

	      String host = "smtp.gmail.com";

	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "587");

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	      new javax.mail.Authenticator() {
	         protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	         }
	      });

	      try {
	         // Create a default MimeMessage object.
	         Message message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.setRecipients(Message.RecipientType.TO,
	         InternetAddress.parse(to));

	         // Set Subject: header field
	         message.setSubject("User Request Approve/Deny");
	         // Now set the actual message
	         message.setContent(message, "text/html");
	         message.setContent("<html>\n" +
	                    "<body>\n" +
	                    "<h4>Hello User , Please Approve or Deny </h4>"+
	                    "<a href=\"http://localhost:8978/MailApi/rest/sendMail/data;flag=Y;param="+to+"\">\n" +
	                    "<input type=\"button\" value=\"Approve\"/>"+
	                    "</a>\n" +
	                    "<a href=\"http://localhost:8978/MailApi/rest/sendMail/data;flag=N;param="+to+"\">\n" +
	                    "<input type=\"button\" value=\"Deny\"/>"+
	                    "</a>\n" +
	                    "</body>\n" +
	                    "</html>", "text/html");
	         // Send message
	         Transport.send(message);
	         return true;
	      } catch (MessagingException e) {
	            throw new RuntimeException(e);
	      }


	}
}
