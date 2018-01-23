package com.feluma.faleconosco.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.feluma.faleconosco.filter.SetorFilter;
import com.feluma.faleconosco.model.Setor;
import com.feluma.faleconosco.model.Unidade;
import com.feluma.faleconosco.service.SetorService;
import com.feluma.faleconosco.service.UnidadeService;
import com.feluma.faleconosco.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaSetorBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private SetorService setorService;
	
	@Inject
	private UnidadeService unidadeService;
	
	private List<Unidade> unidades;
	private List<Setor> setores;
	private Setor setor;
	private SetorFilter filtro;
	
	public PesquisaSetorBean(){
		filtro = new SetorFilter();
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public List<Unidade> getUnidades() {
		return unidades;
	}

	public SetorFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(SetorFilter filtro) {
		this.filtro = filtro;
	}

	public List<Setor> getSetores() {
		return setores;
	}

	@PostConstruct
	public void inicializar(){
		if (!FacesUtil.isPostback()) {
			unidades = unidadeService.listar();
			setores = setorService.listarSetor();
		}
	}
	
	public void pesquisar(){
		setores = setorService.pesquisarSetor(filtro);
	}
	
	public void excluir(Setor setor){
		setorService.excluir(setor);
		setores.remove(setor);
		FacesUtil.addInfoMessage("Setor "+setor.getDescricao()+" excluído com sucesso!");
	}
	
}
