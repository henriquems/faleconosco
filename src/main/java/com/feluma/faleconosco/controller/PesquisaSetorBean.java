package com.feluma.faleconosco.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

<<<<<<< HEAD
import com.feluma.faleconosco.filter.UnidadeSetorFilter;
import com.feluma.faleconosco.model.Unidade;
import com.feluma.faleconosco.model.UnidadeSetor;
import com.feluma.faleconosco.service.UnidadeService;
import com.feluma.faleconosco.service.UnidadeSetorService;
=======
import com.feluma.faleconosco.filter.SetorFilter;
import com.feluma.faleconosco.model.Setor;
import com.feluma.faleconosco.model.Unidade;
import com.feluma.faleconosco.service.SetorService;
import com.feluma.faleconosco.service.UnidadeService;
>>>>>>> ff72ce40a95f98891969da7674da616caff254b4
import com.feluma.faleconosco.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaSetorBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
<<<<<<< HEAD
	private UnidadeSetorService unidadeSetorService;
=======
	private SetorService setorService;
>>>>>>> ff72ce40a95f98891969da7674da616caff254b4
	
	@Inject
	private UnidadeService unidadeService;
	
<<<<<<< HEAD
	private List<UnidadeSetor> unidadeSetores;
	private List<Unidade> unidades;
	private UnidadeSetorFilter filtro;
	
	public PesquisaSetorBean(){
		filtro = new UnidadeSetorFilter();
	}

	public List<UnidadeSetor> getUnidadeSetores() {
		return unidadeSetores;
	}
	
=======
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

>>>>>>> ff72ce40a95f98891969da7674da616caff254b4
	public List<Unidade> getUnidades() {
		return unidades;
	}

<<<<<<< HEAD
	public UnidadeSetorFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(UnidadeSetorFilter filtro) {
		this.filtro = filtro;
	}

=======
	public SetorFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(SetorFilter filtro) {
		this.filtro = filtro;
	}

	public List<Setor> getSetores() {
		return setores;
	}

>>>>>>> ff72ce40a95f98891969da7674da616caff254b4
	@PostConstruct
	public void inicializar(){
		if (!FacesUtil.isPostback()) {
			unidades = unidadeService.listar();
<<<<<<< HEAD
			unidadeSetores = unidadeSetorService.listarUnidadeSetor();
=======
			setores = setorService.listarSetor();
>>>>>>> ff72ce40a95f98891969da7674da616caff254b4
		}
	}
	
	public void pesquisar(){
<<<<<<< HEAD
		unidadeSetores = unidadeSetorService.pesquisarUnidadeSetor(filtro);
=======
		setores = setorService.pesquisarSetor(filtro);
	}
	
	public void excluir(Setor setor){
		setorService.excluir(setor);
		setores.remove(setor);
		FacesUtil.addInfoMessage("Setor "+setor.getDescricao()+" excluído com sucesso!");
>>>>>>> ff72ce40a95f98891969da7674da616caff254b4
	}
	
}
