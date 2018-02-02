package com.feluma.faleconosco.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.feluma.faleconosco.model.Avaliacao;
import com.feluma.faleconosco.model.Mensagem;
import com.feluma.faleconosco.model.StatusAvaliacao;
import com.feluma.faleconosco.model.TipoAssunto;
import com.feluma.faleconosco.service.AvaliacaoService;
import com.feluma.faleconosco.service.EnviaEmailService;
import com.feluma.faleconosco.service.MensagemService;
import com.feluma.faleconosco.util.jsf.FacesUtil;

@Named
@ViewScoped
public class EnviaMensagemBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private MensagemService mensagemService;
	
	@Inject
	private AvaliacaoService avaliacaoService;
	
	@Inject
	private EnviaEmailService enviaEmailService;
	
	private Mensagem mensagem;
	private List<TipoAssunto> tipoAssuntos;
	
	public EnviaMensagemBean(){
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
		mensagem = mensagemService.salvar(mensagem);
		
		Avaliacao avaliacao = new Avaliacao();
		avaliacao.setFlagAtivo(1);
		avaliacao.setMensagem(mensagem);
		avaliacao.setStatusAvaliacao(StatusAvaliacao.ENVIADOPELOCLIENTE);
		avaliacao.setData(new Date());
		
		avaliacaoService.salvar(avaliacao);
		
		enviaEmailService.enviarEmail(avaliacao);
		
		FacesUtil.addInfoMessage("Mensagem enviada com sucesso. Em breve entraremos em contato!");
		return "index.xhtml?faces-redirect=true";
	}

}
