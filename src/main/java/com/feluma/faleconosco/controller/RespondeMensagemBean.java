package com.feluma.faleconosco.controller;

import java.io.Serializable;
import java.util.Date;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.feluma.faleconosco.model.Avaliacao;
import com.feluma.faleconosco.model.Resposta;
import com.feluma.faleconosco.model.StatusAvaliacao;
import com.feluma.faleconosco.security.UsuarioLogado;
import com.feluma.faleconosco.security.UsuarioSistema;
import com.feluma.faleconosco.service.AvaliacaoService;
import com.feluma.faleconosco.service.EnviaEmailService;
import com.feluma.faleconosco.service.RespostaService;
import com.feluma.faleconosco.util.jsf.FacesUtil;

@Named
@ViewScoped
public class RespondeMensagemBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private AvaliacaoService avaliacaoService;
	
	@Inject
	private RespostaService respostaService;
	
	@Inject
	private EnviaEmailService enviaEmailService;
	
	@Inject
	@UsuarioLogado
	private UsuarioSistema usuarioLogado;
	
	private Avaliacao avaliacao;
	private Resposta resposta;
	
	public RespondeMensagemBean(){
		resposta = new Resposta();
	}
	
	public Resposta getResposta() {
		return resposta;
	}

	public void setResposta(Resposta resposta) {
		this.resposta = resposta;
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}
	
	public String responder(){
		avaliacao.setStatusAvaliacao(StatusAvaliacao.RESPONDIDOPELOGERENTE);
		salvar();
		enviaEmailService.enviarEmail(avaliacao);
		
		FacesUtil.addInfoMessage("Mensagem respondida com sucesso!");
		return "pesquisaMensagem.xhtml?faces-redirect=true";
	}
	
	public String rejeitar(){
		avaliacao.setStatusAvaliacao(StatusAvaliacao.REJEITADOPELOGERENTE);
		salvar();
		enviaEmailService.enviarEmail(avaliacao);
		
		FacesUtil.addInfoMessage("Mensagem rejeitada com sucesso!");
		return "pesquisaMensagem.xhtml?faces-redirect=true";
	}
	
	public void salvar(){
		avaliacao.setCodigo(null);
		avaliacao.setUsuario(usuarioLogado.getUsuario());
		avaliacao.setData(new Date());
		
		Long codigoMensagem = avaliacao.getMensagem().getCodigo();
		Long codigoAvaliacao = avaliacaoService.salvar(avaliacao).getCodigo();
		
		avaliacaoService.atualizarAvaliacaoAnterior(codigoAvaliacao, codigoMensagem);
		
		resposta.setMensagem(avaliacao.getMensagem());
		resposta.setUsuario(usuarioLogado.getUsuario());
		resposta.setData(new Date());
		
		respostaService.salvar(resposta);
	}

}
