package com.feluma.faleconosco.util.jms;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;

import com.feluma.faleconosco.util.mail.Email;

public class ProdutorJMS implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Resource(mappedName = "java:/queue/emailQueueFaleConosco")
	private Queue fila;
 
	@Inject
	@JMSConnectionFactory("java:/ConnectionFactory")
	private JMSContext context;
	
	public void produzirMensagem(Email email) {
		try {
			ObjectMessage objMessage = context.createObjectMessage();
			objMessage.setObject(email);
			context.createProducer().send(fila, objMessage);
		} catch (JMSException ex) {
			ex.printStackTrace();
		}
	}

}