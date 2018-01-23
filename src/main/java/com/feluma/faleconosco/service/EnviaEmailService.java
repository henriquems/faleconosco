package com.feluma.faleconosco.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.feluma.faleconosco.dao.PerfilDAO;
import com.feluma.faleconosco.dao.UsuarioDAO;
import com.feluma.faleconosco.model.Avaliacao;
import com.feluma.faleconosco.model.Perfil;
import com.feluma.faleconosco.model.Resposta;
import com.feluma.faleconosco.model.StatusAvaliacao;
import com.feluma.faleconosco.model.Usuario;
import com.feluma.faleconosco.util.jms.ProdutorJMS;
import com.feluma.faleconosco.util.mail.Email;

public class EnviaEmailService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioDAO daoUsuario;
	
	@Inject
	private PerfilDAO daoPerfil;
	
	@Inject
	private ProdutorJMS produtorJMSService;
	
	public void enviarEmail(Avaliacao avaliacao) {
		if(avaliacao.getStatusAvaliacao().equals(StatusAvaliacao.ENVIADOPELOCLIENTE) ||
		   avaliacao.getStatusAvaliacao().equals(StatusAvaliacao.RESPONDIDOPELOGERENTE) ||
		   avaliacao.getStatusAvaliacao().equals(StatusAvaliacao.REJEITADOPELOGERENTE)){
			enviarEmailParaOuvidor();
		} else if(avaliacao.getStatusAvaliacao().equals(StatusAvaliacao.AVALIADOPELOOUVIDOR)){
			enviarEmailParaGerente(avaliacao);
		} else if(avaliacao.getStatusAvaliacao().equals(StatusAvaliacao.RESPONDIDOPELOOUVIDOR)){
			enviarEmailParaCliente(avaliacao);
		}
	}
	
	private void enviarEmailParaOuvidor() {
		Perfil perfil = daoPerfil.recuperarPerfilPorNome("OUVIDOR");
		List<Usuario> usuarios = daoUsuario.recuperarUsuariosPorPerfil(perfil);
		
		for(Usuario usuario : usuarios){
			StringBuffer sb = new StringBuffer();
			sb.append("Olá Ouvidor, "); sb.append(usuario.getNome());
			sb.append("<br /><br />Uma nova mensagem foi enviada de um cliente pelo sistema de Fale Conosco da Ouvidoria.");
			sb.append("<br />Por favor, acesse o sistema pelo link abaixo e verifique a mensagem.");
			sb.append("<br /><br />http://localhost:8080/faleconosco/login.xhtml");
			sb.append("<br /><br />Essa é uma mensagem automática, favor nao responder!");
			sb.append("<br /><br />Att. <br />Departamento de Informática Feluma");
			
			enviar(usuario.getEmail(), sb.toString());
		}
	}
	
	private void enviarEmailParaGerente(Avaliacao avaliacao) {
		Usuario usuario = daoUsuario.recuperarGerentePorUnidadeSetor(avaliacao.getUnidadeSetor());
		
		StringBuffer sb = new StringBuffer();
		sb.append("Olá, Gerente "); sb.append(usuario.getNome());
		sb.append("<br /><br />Uma nova mensagem foi enviada por um ouvidor pelo sistema de Fale Conosco da Ouvidoria.");
		sb.append("<br />Por favor, acesse o sistema pelo link abaixo e verifique a mensagem.");
		sb.append("<br /><br />http://localhost:8080/faleconosco/login.xhtml");
		sb.append("<br /><br />Essa é uma mensagem automática, favor nao responder!");
		sb.append("<br /><br />Att. <br />Departamento de Informática Feluma");
		
		enviar(usuario.getEmail(), sb.toString());
	}
	
	private void enviarEmailParaCliente(Avaliacao avaliacao) {
		StringBuffer sb = new StringBuffer();
		sb.append("Olá Cliente, "); sb.append(avaliacao.getMensagem().getCliente().getNome());
		sb.append("<br /><br />Sobre a mensagem enviada pelo Sistema de Fale Conosco da Ouvidoria, temos as seguintes respostas.");
		sb.append("<br /> Assunto: "); sb.append(avaliacao.getMensagem().getTipoAssunto().getDescricao());
		sb.append("<br /> Mensagem: "); sb.append(avaliacao.getMensagem().getDescricao());
		sb.append("<br /><br />");
		sb.append("Respostas obtidas");
		
		for(Resposta resposta : avaliacao.getMensagem().getRespostas()){
			sb.append("<br />Resposta: "); sb.append(resposta.getDescricao());
		}
		
		sb.append("<br /><br />Essa é uma mensagem automática, favor nao responder!");
		sb.append("<br /><br />Att. <br />Departamento de Informática Feluma");
		
		enviar(avaliacao.getMensagem().getCliente().getEmail(), sb.toString());
	}
	
	public void enviar(String emailDestinatario, String mensagem){
		Email email = new Email();
		email.setRemetente("sistemas@cienciasmedicasmg.edu.br");
		email.setDestinatario(emailDestinatario);
		email.setAssunto("Fale Conosco Ouvidoria");
		email.setMensagem(mensagem);
		
		produtorJMSService.produzirMensagem(email);
	}
	
}
