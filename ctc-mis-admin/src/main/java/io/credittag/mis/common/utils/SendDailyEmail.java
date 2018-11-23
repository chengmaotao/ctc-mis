package io.credittag.mis.common.utils;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.sun.mail.util.MailSSLSocketFactory;

public class SendDailyEmail {
	
	public static void send(String from,String password ,String to,String host,String port,MimeMultipart content,String subject) {

		
		
		 List<InternetAddress> list = new ArrayList<InternetAddress>();
		 InternetAddress[] addr = null;
		String[] tos = to.split(",");
		for(int i=0;i<tos.length;i++) {
			 try {
				list.add(new InternetAddress(tos[i]));
				addr = list.toArray(new InternetAddress[list.size()]);
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// 获取系统属性
		Properties properties = System.getProperties();

		// 设置邮件服务器
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.smtp.port", port);
		properties.setProperty("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.auth", "true");
		
		
		properties.setProperty("mail.user", from);
		properties.setProperty("mail.password", password);

		try {
			MailSSLSocketFactory sf;
			sf = new MailSSLSocketFactory();
			sf.setTrustAllHosts(true);
			properties.put("mail.smtp.ssl.enable", "true");
			properties.put("mail.smtp.ssl.socketFactory", sf);
			
		      
		} catch (GeneralSecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// 获取默认session对象
		Session session = Session.getDefaultInstance(properties, new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password); // 发件人邮件用户名、密码
			}
		});
		 //Session session = Session.getDefaultInstance(properties);
		try {
			// 创建默认的 MimeMessage 对象
			MimeMessage message = new MimeMessage(session);

			// Set From: 头部头字段
			message.setFrom(new InternetAddress(from));

			// Set To: 头部头字段
			message.addRecipients(Message.RecipientType.TO, addr);
			
			// Set Subject: 头部头字段
			message.setSubject(subject);

			// 设置消息体
			message.setContent(content);
			

			// 发送消息
			Transport.send(message);
			System.out.println("Sent message successfully....from runoob.com");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
