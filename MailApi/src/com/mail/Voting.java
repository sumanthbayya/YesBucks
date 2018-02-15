package com.mail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Voting {
	public static void main(String[] args) {

		String fromEmailTxt = null;
		final String password = "V@sudeva149";
		final String toEmail;
		String toEmailTxt = null;
		StringBuilder toList = new StringBuilder();
		// toList.append("kumarvinay149@gmail.com,pawankumar.pcy@thirdware.com");
		File file = new File("C:\\Email\\mailID.txt");

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String st;
			while ((st = br.readLine()) != null) {
				System.out.println(st);
				if (st.contains("from:")) {
					String[] result = st.split("from:");
					fromEmailTxt = result[1];
				}
				if (st.contains("to:")) {
					String[] result1 = st.split("to:");
					toEmailTxt = result1[1];
				}
			}

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		final String fromEmail = fromEmailTxt.trim();
		toEmail = toEmailTxt.trim();
		System.out.println("SSLEmail Start");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP Host
		props.put("mail.smtp.socketFactory.port", "465"); // SSL Port
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true"); // Enabling SMTP Authentication
		props.put("mail.smtp.port", "465"); // SMTP Port

		Authenticator auth = new Authenticator() {
			// override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};

		Session session = Session.getDefaultInstance(props, auth);
		System.out.println("Session created");
		MimeMessage message = new MimeMessage(session);

		try {
			StringBuilder messageBody = new StringBuilder();
			messageBody.append("<!DOCTYPE html> <html> <body>");
			messageBody.append("<a href=\"#\"> <input type=\"button\" value=\"Yes\"> </a>");
			messageBody.append("<a href=\"#\"> <input type=\"button\" value=\"No\">  </a>");

			messageBody.append("</body> </html>");
			message.setFrom(new InternetAddress(fromEmail));
			message.setHeader("Content-Type", "text/html");
			message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
			message.setSubject("Voting!!!");
			MimeBodyPart mimeBodyPart = new MimeBodyPart();
			mimeBodyPart.setContent(messageBody, "text/html");
			MimeMultipart multipart = new MimeMultipart();
			multipart.addBodyPart(mimeBodyPart);
			message.setContent("<html>\n" +
	                    "<body>\n" +
	                    "<h4>Is Tajmahal in India ?  </h4>"+
	                    "<a href=\"#\">\n" +
	                    "<input type=\"button\" value=\"Yes\"/>"+
	                    "</a>\n" +
	                    "<a href=\"#\">\n" +
	                    "<input type=\"button\" value=\"No\"/>"+
	                    "</a>\n" +
	                    "</body>\n" +
	                    "</html>", "text/html");
			message.setText(messageBody.toString(), "UTF-8", "html");
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

