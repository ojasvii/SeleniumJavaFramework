package utilities;


import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MonitoringMail {
	
/*	
	public void sendMail(String mailServer, String from, String[] to, String subject, String messageBody, String attachmentPath, String attachmentName){
		
		
		boolean debug = false;
		Properties props = new Properties();
		
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.EnableSSL.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", mailServer);
		props.put("mail.debug", "true");
		
		
		props.setProperty(key, value);
		props.setProperty(key, value);
		props.setProperty(key, value);
		props.setProperty(key, value);
		
		
		Authenticator auth = new SMTPAuthenticator();
	      // Get the default Session object.
	      Session session = Session.getDefaultInstance(props);
	      
	      Transport bus = session.getTransport("smtp");
	      bus.connect();

	      try {
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);
	         
	         message.addHeader("X-Priority", "1");

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         InternetAddress[] addressTo = new InternetAddress();
	         
	         for(int i=0; i<to.length; i++){
	        	 
	        	 addressTo[i] = new InternetAddress(to[i]);
	        	 message.setRecipients(Message.RecipientType.TO, new InternetAddress());
	        	 message.setSubject(subject);

	         }

	         // Set Subject: header field
	         message.setSubject("This is the Subject Line!");

	         // Create the message part 
	         BodyPart body = new MimeBodyPart();

	         // Fill the message
	        // body.setText("This is message body");
	         
	         body.setContent(messageBody, "text/html ");
	         
	         BodyPart attachment = new MimeBodyPart();
	         DataSource source = new FileDataSource(attachment);
	         
	         // Create a multipar message
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);

	         // Part two is attachment
	         messageBodyPart = new MimeBodyPart();
	         String filename = "file.txt";
	         DataSource source = new FileDataSource(filename);
	         messageBodyPart.setDataHandler(new DataHandler(source));
	         messageBodyPart.setFileName(filename);
	         multipart.addBodyPart(messageBodyPart);

	         // Send the complete message parts
	         message.setContent(multipart );

	         // Send message
	         Transport.send(message);
	         System.out.println("Sent message successfully....");
	      } catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	 
		
		
		
		
	}

	   public static void main(String [] args) {     
		   
		   
		   
		       }*/
		}