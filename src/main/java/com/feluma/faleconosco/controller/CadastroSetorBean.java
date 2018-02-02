package com.feluma.faleconosco.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.feluma.faleconosco.model.Unidade;
import com.feluma.faleconosco.model.UnidadeSetor;
import com.feluma.faleconosco.service.UnidadeService;
import com.feluma.faleconosco.service.UnidadeSetorService;
import com.feluma.faleconosco.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroSetorBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UnidadeSetorService unidadeSetorService;
	
	@Inject
	private UnidadeService unidadeService;
	
	private UnidadeSetor unidadeSetor;
	private List<Unidade> unidades;
	
	public CadastroSetorBean(){
		unidadeSetor = new UnidadeSetor();
	}

	public UnidadeSetor getUnidadeSetor() {
		return unidadeSetor;
	}

	public void setUnidadeSetor(UnidadeSetor unidadeSetor) {
		this.unidadeSetor = unidadeSetor;
	}
	
	public List<Unidade> getUnidades() {
		return unidades;
	}

	@PostConstruct
	public void inicializar(){
		if (!FacesUtil.isPostback()) {
			unidades = unidadeService.listar();
		}
	}
	
	public boolean isEditando(){
		Boolean editando = false;
		if(unidadeSetor.getCodigo() != null){
			editando = true;
			unidadeSetor = unidadeSetorService.recuperarUnidadeSetor(unidadeSetor.getCodigo());
		}	
		return editando;
	}

}
