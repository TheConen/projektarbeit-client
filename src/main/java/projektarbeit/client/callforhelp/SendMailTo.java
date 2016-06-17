package projektarbeit.client.callforhelp;

//File Name SendEmail.java

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SendMailTo {
	
 // Recipient's email ID needs to be mentioned.
 String to = "khargoth24@t-online.de";

 String host = "smtp.gmx.de";

 public void sendToRecipient(String sendTo) {
	 Properties properties = System.getProperties();

	 // Properties for Sending
	 properties.setProperty("mail.smtp.host", host);
	 
	 properties.put("mail.smtp.starttls.enable", "true");
	 properties.put("mail.smtp.port", "587");
	 properties.setProperty("mail.debug", "true");
	 properties.put("mail.smtp.auth", "true");
	 
	 Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
	     protected PasswordAuthentication getPasswordAuthentication() {
	         return new PasswordAuthentication(sendTo, "");
	     }
	 });

	 try{
	    // Create a default MimeMessage object.
	    MimeMessage message = new MimeMessage(session);

	    // Set From: header field of the header.
	    message.setFrom(new InternetAddress(sendTo));

	    // Set To: header field of the header.
	    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

	    // Set Subject: header field
	    message.setSubject("Emergency Call!");

	    // Now set the actual message
	    message.setText("Hello, \n \n"
	    		+ "I need help. Please come. Something happened. \n \n"
	    		+ "Greetings, \n"
	    		+ "Daniel Rothmann");

	    // Send message
	    Transport.send(message);
	    System.out.println("Sent message successfully....");
	 }catch (MessagingException mex) {
	    mex.printStackTrace();
	 }
 }
 
 
}