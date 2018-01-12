package com.feluma.faleconosco.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.feluma.faleconosco.model.Mensagem;
import com.feluma.faleconosco.model.TipoAssunto;
import com.feluma.faleconosco.service.MensagemService;
import com.feluma.faleconosco.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroMensagemBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private MensagemService mensagemService;
	
	private Mensagem mensagem;
	private List<TipoAssunto> tipoAssuntos;
	
	public CadastroMensagemBean(){
		mensagem = new Mensagem();
	}

	public Mensagem getMensagem() {
		return mensagem;
	}

	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}
	
	public List<TipoAssunto> getTipoAssuntos() {
		return tipoAssuntos;
	}
	
	@PostConstruct
	public void inicializar(){
		if(!FacesUtil.isPostback()){
			tipoAssuntos = Arrays.asList(TipoAssunto.values());
		}
	}

	public String salvar(){
		mensagem.setData(new Date());
		mensagem.getCliente().setData(new Date());
		mensagemService.salvar(mensagem);
		FacesUtil.addInfoMessage("Mensagem enviada com sucesso! Em breve entraremos em contato!");
		return "index.xhtml?faces-redirect=true";
	}

}
