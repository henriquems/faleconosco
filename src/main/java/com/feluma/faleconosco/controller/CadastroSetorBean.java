package com.feluma.faleconosco.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.feluma.faleconosco.util.jsf.FacesUtil;
import com.feluma.faleconosco.model.Setor;
import com.feluma.faleconosco.model.Unidade;
import com.feluma.faleconosco.service.SetorService;
import com.feluma.faleconosco.service.UnidadeService;

@Named
@ViewScoped
public class CadastroSetorBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private SetorService setorService;
	
	@Inject
	private UnidadeService unidadeService;
	
	private Unidade unidade;
	private Setor setor;
	private List<Unidade> unidades;
	
	public CadastroSetorBean(){
		setor = new Setor();
	}
	
	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public List<Unidade> getUnidades() {
		return unidades;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}
	
	@PostConstruct
	public void inicializar() {
		if(!FacesUtil.isPostback()){
			unidades = unidadeService.listar();
		}
	}

	public boolean isEditando(){
		Boolean editando = false;
		if(setor.getCodigo() != null){
			editando = true;
			setor = setorService.recuperarSetor(setor.getCodigo());
		}	
		return editando;
	}
	
	public String salvar(){
		String retorno = null;
		if(setor.getUnidades().size() != 0){
			setor = setorService.salvar(setor);
			FacesUtil.addInfoMessage("Setor "+setor.getDescricao()+" salvo com sucesso!");
			retorno = "pesquisaSetor.xhtml?faces-redirect=true";
		} else {
			FacesUtil.addAlertMessage("Favor informar pelo menos uma unidade!");
		}
		return retorno;
	}
	
	public void adicionarUnidade(){
		if(unidade != null){
			if(setor.getUnidades().contains(unidade)){
				FacesUtil.addErroMessage("A unidade selecionada já se encontra adicionada!");
			} else {
				setor.getUnidades().add(unidade);
				FacesUtil.addAlertMessage("Unidade adicionada com sucesso! Favor salvar os dados!");
			}
		} else {
			FacesUtil.addErroMessage("Favor selecionar uma unidade!");
		}
	}
	
	public void excluirUnidade(Unidade unidade){
		setor.getUnidades().remove(unidade);
	}

}
