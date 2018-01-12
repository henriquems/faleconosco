package com.feluma.faleconosco.util.mail;

import java.io.IOException;
import java.util.Properties;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import com.outjected.email.api.SessionConfig;
import com.outjected.email.impl.SimpleMailConfig;

//Classe produtora de SessionConfig da API SimpleMail
public class MailConfigProducer {
	
	
	//Método produtor de SessionConfig
	//Toda vez que precisar de uma config, o método produtor abaixo entrega uma instancia 
	//de SessionConfig da API SimpleMail.
	//Portanto agora conseguimos fazer a injeção de SessionConfig na classe Mailer
	@Produces
	@ApplicationScoped
	public SessionConfig getMailConfig() throws IOException{
		Properties props = new Properties();
		props.load(getClass().getResourceAsStream("/mail.properties"));
		
		SimpleMailConfig config = new SimpleMailConfig();
		config.setServerHost(props.getProperty("mail.server.host"));
		config.setServerPort(Integer.parseInt(props.getProperty("mail.server.port")));
		config.setEnableSsl(Boolean.parseBoolean(props.getProperty("mail.enable.ssl")));
		config.setAuth(Boolean.parseBoolean(props.getProperty("mail.auth")));
		config.setUsername(props.getProperty("mail.username"));
		config.setPassword(props.getProperty("mail.password"));
		
		return config;
	}
	
}
