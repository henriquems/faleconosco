package com.feluma.faleconosco.controller;

import java.io.Serializable;
import java.util.Date;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.feluma.faleconosco.model.Avaliacao;
import com.feluma.faleconosco.model.MensagemOriginal;
import com.feluma.faleconosco.model.Resposta;
import com.feluma.faleconosco.security.UsuarioLogado;
import com.feluma.faleconosco.security.UsuarioSistema;
import com.feluma.faleconosco.service.MensagemOriginalService;
import com.feluma.faleconosco.service.RespostaService;
import com.feluma.faleconosco.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroRespostaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private RespostaService respostaService;
	
	@Inject
	private MensagemOriginalService mensagemOriginalService;
	
	@Inject
	@UsuarioLogado
	private UsuarioSistema usuarioLogado;
	
	private Resposta resposta;
	private Avaliacao avaliacao;

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

	public String salvar(){
		Resposta res = respostaService.recuperarResposta(resposta.getCodigo());
		
		MensagemOriginal mensagemOriginal = new MensagemOriginal();
		mensagemOriginal.setUsuario(usuarioLogado.getUsuario());
		mensagemOriginal.setResposta(res);
		mensagemOriginal.setDescricao(res.getDescricao());
		mensagemOriginal.setData(new Date());
		
		respostaService.salvar(resposta);
		mensagemOriginalService.salvar(mensagemOriginal);
		
		FacesUtil.addInfoMessage("Resposta editada com sucesso!");
		StringBuffer sb = new StringBuffer();
		sb.append("/mensagem/encaminhaMensagem.xhtml?avaliacao=");
		sb.append(avaliacao.getCodigo()); sb.append("&faces-redirect=true");
		
		return sb.toString();
	}

}
