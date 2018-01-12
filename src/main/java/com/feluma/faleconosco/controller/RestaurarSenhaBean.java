package com.feluma.faleconosco.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.feluma.faleconosco.model.Usuario;
import com.feluma.faleconosco.service.UsuarioService;
import com.feluma.faleconosco.util.jms.ProdutorJMS;
import com.feluma.faleconosco.util.jsf.FacesUtil;
import com.feluma.faleconosco.util.mail.Email;

@Named
@ViewScoped
public class RestaurarSenhaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioService usuarioService;
	
	@Inject
	private ProdutorJMS produtorJMSService;
	
	private String cpf;
	private String nascimento;
	
	public RestaurarSenhaBean(){
		limpar();
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNascimento() {
		return nascimento;
	}

	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}
	
	public void limpar(){
		this.cpf = null;
		this.nascimento = null;
	}
	
	public void restaurarSenha(){
		StringBuffer sb = new StringBuffer();
		Usuario usuario = usuarioService.recuperarUsuarioPorCpf(cpf);
		
		if(usuario != null) {
			String senha = usuarioService.restaurarSenha(usuario);
			montarMensagemParaEnviarEmailParaRestaurarSenha(usuario, senha);
			sb.append("Sua nova senha foi enviada para "+usuario.getEmail());
			FacesUtil.addInfoMessage(sb.toString());
		} else {
			limpar();
			sb.append("Não foi encontrado nenhum usuário com o cpf informado!");
			FacesUtil.addErroMessage(sb.toString());
		}
	}
	
	private void montarMensagemParaEnviarEmailParaRestaurarSenha(Usuario usuario, String senha) {
		StringBuffer sb = new StringBuffer();
		sb.append("Olá, "+usuario.getNome()+"!");
		sb.append("<br /><br />Sua senha no sistema de Plano de Ensino foi restaurada com sucesso!");
		sb.append("<br />Nova Senha: "); sb.append(senha);
		sb.append("<br /><br />Essa é uma mensagem automática, favor nao responder!");
		sb.append("<br /><br />Att. <br />Equipe Informática Feluma");
		
		Email email = new Email();
		email.setRemetente("sistemas@cienciasmedicasmg.edu.br");
		email.setDestinatario(usuario.getEmail());
		email.setAssunto("Sistema de Plano de Ensino CMMG");
		email.setMensagem(sb.toString());
		
		produtorJMSService.produzirMensagem(email);
	}
	
}