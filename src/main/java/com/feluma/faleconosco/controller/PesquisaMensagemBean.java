package com.feluma.faleconosco.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.feluma.faleconosco.filter.AvaliacaoFilter;
import com.feluma.faleconosco.model.Avaliacao;
import com.feluma.faleconosco.model.StatusAvaliacao;
import com.feluma.faleconosco.security.Seguranca;
import com.feluma.faleconosco.security.UsuarioLogado;
import com.feluma.faleconosco.security.UsuarioSistema;
import com.feluma.faleconosco.service.AvaliacaoService;
import com.feluma.faleconosco.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaMensagemBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private AvaliacaoService avaliacaoService;
	
	@Inject
	private Seguranca seguranca;
	
	@Inject
	@UsuarioLogado
	private UsuarioSistema usuarioLogado;
	
	private List<Avaliacao> avaliacoes;
	private AvaliacaoFilter filtro;
	private List<StatusAvaliacao> statusAvaliacoes;
	
	public PesquisaMensagemBean(){
		filtro = new AvaliacaoFilter();
	}
	
	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}
	
	public AvaliacaoFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(AvaliacaoFilter filtro) {
		this.filtro = filtro;
	}

	public List<StatusAvaliacao> getStatusAvaliacoes() {
		return statusAvaliacoes;
	}

	@PostConstruct
	public void inicializar(){
		if (!FacesUtil.isPostback()) {
			if(seguranca.getUsuario().getPerfis().get(0).getNome().equals("OUVIDOR")){
				avaliacoes = avaliacaoService.listarAvaliacaoParaOuvidor();
			} else if(seguranca.getUsuario().getPerfis().get(0).getNome().equals("GERENTE")){
				avaliacoes = avaliacaoService.listarAvaliacaoParaGerente(usuarioLogado.getUsuario().getUnidadeSetor());
			}
			statusAvaliacoes = Arrays.asList(StatusAvaliacao.values());
		}
	}
	
	public void pesquisar(){
		avaliacoes = avaliacaoService.pesquisarAvaliacao(filtro);
	}
	
}
