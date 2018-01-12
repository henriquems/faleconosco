package com.feluma.faleconosco.util.mail;

import java.io.Serializable;

import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import com.outjected.email.api.MailMessage;
import com.outjected.email.api.SessionConfig;
import com.outjected.email.impl.MailMessageImpl;

@RequestScoped
public class Mailer implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//Session config = Configuração da sessão de envio de e-mail 
	//Precisamos injetar a Session config pois ela pertence a API do SimpleMail
	//Mas não conseguimos fazer o injet normalmente porque o SessionConfig da SimpleMail não é um Bean CDI
	//Portanto precisamos criar um produtor de SessionConfig
	//Será criado a classe MailConfigProducer
	@Inject
	private SessionConfig config;
	
	//Retorna a impletementação de MailMessage tendo o config passado como parâmetro
	public MailMessage novaMensagem(){
		return new MailMessageImpl(this.config);
	}
	
	
}
