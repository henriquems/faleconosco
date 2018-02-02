package com.feluma.faleconosco.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.feluma.faleconosco.filter.UnidadeSetorFilter;
import com.feluma.faleconosco.model.Unidade;
import com.feluma.faleconosco.model.UnidadeSetor;
import com.feluma.faleconosco.service.UnidadeService;
import com.feluma.faleconosco.service.UnidadeSetorService;
import com.feluma.faleconosco.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaSetorBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UnidadeSetorService unidadeSetorService;
	
	@Inject
	private UnidadeService unidadeService;
	
	private List<UnidadeSetor> unidadeSetores;
	private List<Unidade> unidades;
	private UnidadeSetorFilter filtro;
	
	public PesquisaSetorBean(){
		filtro = new UnidadeSetorFilter();
	}

	public List<UnidadeSetor> getUnidadeSetores() {
		return unidadeSetores;
	}
	
	public List<Unidade> getUnidades() {
		return unidades;
	}

	public UnidadeSetorFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(UnidadeSetorFilter filtro) {
		this.filtro = filtro;
	}

	@PostConstruct
	public void inicializar(){
		if (!FacesUtil.isPostback()) {
			unidades = unidadeService.listar();
			unidadeSetores = unidadeSetorService.listarUnidadeSetor();
		}
	}
	
	public void pesquisar(){
		unidadeSetores = unidadeSetorService.pesquisarUnidadeSetor(filtro);
	}
	
}
