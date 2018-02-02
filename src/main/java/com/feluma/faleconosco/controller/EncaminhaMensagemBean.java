package com.feluma.faleconosco.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.feluma.faleconosco.model.Avaliacao;
import com.feluma.faleconosco.model.Setor;
import com.feluma.faleconosco.model.StatusAvaliacao;
import com.feluma.faleconosco.model.Unidade;
import com.feluma.faleconosco.security.UsuarioLogado;
import com.feluma.faleconosco.security.UsuarioSistema;
import com.feluma.faleconosco.service.AvaliacaoService;
import com.feluma.faleconosco.service.EnviaEmailService;
import com.feluma.faleconosco.service.SetorService;
import com.feluma.faleconosco.service.UnidadeService;
import com.feluma.faleconosco.util.jsf.FacesUtil;

@Named
@ViewScoped
public class EncaminhaMensagemBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UnidadeService unidadeService;
	
	@Inject
	private SetorService setorService;
	
	@Inject
	private AvaliacaoService avaliacaoService;
	
	@Inject
	private EnviaEmailService enviaEmailService;
	
	@Inject
	@UsuarioLogado
	private UsuarioSistema usuarioLogado;
	
	private Avaliacao avaliacao;
	private List<Unidade> unidades;
	private List<Setor> setores;
	
	public Avaliacao getAvaliacao() {
		return avaliacao;
	}
	
	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}
	
	public List<Unidade> getUnidades() {
		return unidades;
	}

	public List<Setor> getSetores() {
		return setores;
	}

	public void inicializar() {
		if(!FacesUtil.isPostback()){
			unidades = unidadeService.listar();
			recuperarSetoresDaUnidade();
		}
	}
	
	public void recuperarSetoresDaUnidade(){
		if(avaliacao.getUnidadeSetor().getId().getUnidade() != null){
			setores = setorService.recuperarSetoresDaUnidade(avaliacao.getUnidadeSetor().getId().getUnidade().getCodigo());
		}
	}
	
	public String encaminharParaGerente(){
		avaliacao.setStatusAvaliacao(StatusAvaliacao.AVALIADOPELOOUVIDOR);
		salvar();
		enviaEmailService.enviarEmail(avaliacao);
		
		FacesUtil.addInfoMessage("Mensagem encaminhada para o gerente com sucesso!");
		return "pesquisaMensagem.xhtml?faces-redirect=true";
	}
	
	public String responderParaCliente(){
		avaliacao.setStatusAvaliacao(StatusAvaliacao.RESPONDIDOPELOOUVIDOR);
		salvar();
		enviaEmailService.enviarEmail(avaliacao);
		
		FacesUtil.addInfoMessage("Mensagem encaminhada para o cliente com sucesso!");
		return "pesquisaMensagem.xhtml?faces-redirect=true";
	}
	
	public void salvar(){
		avaliacao.setCodigo(null);
		avaliacao.setUsuario(usuarioLogado.getUsuario());
		avaliacao.setFlagAtivo(1);
		avaliacao.setData(new Date());
		
		Long codigoMensagem = avaliacao.getMensagem().getCodigo();
		Long codigoAvaliacao = avaliacaoService.salvar(avaliacao).getCodigo();
		
		avaliacaoService.atualizarAvaliacaoAnterior(codigoAvaliacao, codigoMensagem);
	}
	
	
}
