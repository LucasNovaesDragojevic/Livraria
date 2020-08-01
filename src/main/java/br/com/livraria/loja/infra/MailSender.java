package br.com.livraria.loja.infra;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@ApplicationScoped
public class MailSender 
{
	@Resource(mappedName = "java:/jboss/mail/gmail")
	private Session session;

	public void send(String from, String to, String subject, String content) 
	{
		MimeMessage message = new MimeMessage(session);
		try 
		{
			message.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(to));
			message.setFrom(new InternetAddress(from));
			message.setSubject(subject);
			message.setContent(content, "text/html");
			Transport.send(message);
			System.out.println("E-mail enviado.");
		} 
		catch (MessagingException e) 
		{
			throw new RuntimeException(e);
		}
	}
}
