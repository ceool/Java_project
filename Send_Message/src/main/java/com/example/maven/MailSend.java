package com.example.maven;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

class MailSend {
	public static void main(String[] args) {
		final String user = "이메일 계정"; // 네이버일 경우 네이버 계정, gmail경우 gmail 계정
	    final String password = "pw";   // 패스워드

	        // SMTP 서버 정보를 설정한다.
	        Properties prop = new Properties();
	        prop.put("mail.smtp.host", "smtp.gmail.com"); 
	        prop.put("mail.smtp.port", 465); 
	        prop.put("mail.smtp.auth", "true"); 
	        prop.put("mail.smtp.ssl.enable", "true"); 
	        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	        
	        Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(user, password);
	            }
	        });

	        try {
	            MimeMessage message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(user));

	            //수신자메일주소
	            message.addRecipient(Message.RecipientType.TO, new InternetAddress("발을 이메일 주소")); 

	            // Subject
	            message.setSubject("제목"); //메일 제목을 입력

	            // Text
	            message.setText("성공");    //메일 내용을 입력

	            // send the message
	            Transport.send(message); ////전송
	            System.out.println("message sent successfully...");
	        } catch (AddressException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (MessagingException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	}
}