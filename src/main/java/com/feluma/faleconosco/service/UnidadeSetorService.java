package com.feluma.faleconosco.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.feluma.faleconosco.dao.UnidadeSetorDAO;
import com.feluma.faleconosco.filter.UnidadeSetorFilter;
import com.feluma.faleconosco.model.UnidadeSetor;

public class UnidadeSetorService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UnidadeSetorDAO dao;

	public List<UnidadeSetor> recuperarSetoresDaUnidade(Long codigo) {
		return dao.recuperarSetoresDaUnidade(codigo);
	}

	public UnidadeSetor recuperarUnidadeSetor(Long codigoUnidade, Long codigoSetor) {
		return dao.recuperarUnidadeSetor(codigoUnidade, codigoSetor);
	}

	public List<UnidadeSetor> listarUnidadeSetor() {
		return dao.listarUnidadeSetor();
	}

	public List<UnidadeSetor> pesquisarUnidadeSetor(UnidadeSetorFilter filtro) {
		return dao.pesquisarUnidadeSetor(PesquisaService.carregaListaParametrosPesquisa(filtro));
	}

	public UnidadeSetor recuperarUnidadeSetor(Long codigo) {
		return dao.recuperarUnidadeSetor(codigo);
	}	
	
}
