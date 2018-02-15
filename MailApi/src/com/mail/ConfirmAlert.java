package com.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.model.Vote;

public class ConfirmAlert {
	
	
	public void sentResponse(Vote v){
		
		validate("dummyname8885899@gmail.com", "dummyname8885899","boogie123", v.getUsername(), v.getVote());
		
		
		
	}
	
	public static boolean validate(String from,final String username,final String password,String to,String otp){
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
	         String resp = "";
	         if(otp.equals("Y")){
	        	 resp="Approved";
	         }
	         else{
	        	 resp="Denied";
	         }
	         
	         // Set Subject: header field
	         message.setSubject("Voting Campign Response Recieved");
	         // Now set the actual message
	         message.setText("Hi User, ");
	         message.setContent(message, "text/html");
	         message.setContent("<html>\n" +
	                    "<body>\n" +
	                    "<h4>Thank you, You have responded <b>' "+resp+" '</b> </h4>"+
	                    "</html>", "text/html");
	         // Send message
	         Transport.send(message);
	         return true;
	      } catch (MessagingException e) {
	            throw new RuntimeException(e);
	      }


	}
}
