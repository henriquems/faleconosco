package com.feluma.faleconosco.controller;

import java.io.Serializable;
import java.util.Date;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.feluma.faleconosco.security.UsuarioLogado;
import com.feluma.faleconosco.security.UsuarioSistema;
import com.feluma.faleconosco.model.Avaliacao;
import com.feluma.faleconosco.model.Mensagem;
import com.feluma.faleconosco.model.MensagemOriginal;
import com.feluma.faleconosco.service.MensagemOriginalService;
import com.feluma.faleconosco.service.MensagemService;
import com.feluma.faleconosco.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroMensagemBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private MensagemService mensagemService;
	
	@Inject
	private MensagemOriginalService mensagemOriginalService;
	
	@Inject
	@UsuarioLogado
	private UsuarioSistema usuarioLogado;
	
	private Avaliacao avaliacao;

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}
	
	public String salvar(){
		Mensagem mensagem = mensagemService.recuperarMensagem(avaliacao.getMensagem().getCodigo());
		
		MensagemOriginal mensagemOriginal = new MensagemOriginal();
		mensagemOriginal.setUsuario(usuarioLogado.getUsuario());
		mensagemOriginal.setMensagem(mensagem);
		mensagemOriginal.setDescricao(mensagem.getDescricao());
		mensagemOriginal.setData(new Date());
		
		mensagemService.salvar(avaliacao.getMensagem());
		mensagemOriginalService.salvar(mensagemOriginal);
		
		FacesUtil.addInfoMessage("Mensagem alterada com sucesso!");
		
		return "pesquisaMensagem.xhtml?faces-redirect=true";
	}

}
