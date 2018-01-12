package com.feluma.faleconosco.util.jms;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import com.feluma.faleconosco.util.mail.Email;
import com.feluma.faleconosco.util.mail.Mailer;
import com.outjected.email.api.MailMessage;
 
/**
 * Message-Driven Bean implementation class for: EmailMDB
 */
@MessageDriven(activationConfig = {
@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/queue/emailQueueFaleConosco") }, mappedName = "java:/queue/emailQueueFaleConosco")
public class EmailQueueMDB implements MessageListener {
	
	@Inject
	private Mailer mailer;
	
	public EmailQueueMDB() {}
 
	public void onMessage(Message message) {
		System.out.println(getClass() + " Inicio");
		try {
 			if (message instanceof TextMessage) {
				TextMessage mensagem = (TextMessage) message;
				System.out.println("Mensagem recebida: " + mensagem.getText());
			} else if (message instanceof ObjectMessage) {
				try {
					ObjectMessage obj = (ObjectMessage) message;
					Email email = (Email) obj.getObject();
					enviarEmail(email);
					System.out.println("De: " + email.getRemetente()+ " - Para: "+email.getDestinatario());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(getClass() + " Fim");
	}
	
	public void enviarEmail(Email email){
		MailMessage message = mailer.novaMensagem();
		message.from(email.getRemetente());
		message.to(email.getDestinatario());
		message.subject(email.getAssunto());
		message.bodyHtml(email.getMensagem());
		message.send();
	}
}