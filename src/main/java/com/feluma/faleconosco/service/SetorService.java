package com.feluma.faleconosco.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.feluma.faleconosco.dao.SetorDAO;
import com.feluma.faleconosco.filter.SetorFilter;
import com.feluma.faleconosco.model.Setor;

public class SetorService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private SetorDAO dao;
	
	public List<Setor> listar() {
		return dao.listar();
	}
	
	public List<Setor> recuperarSetoresDaUnidade(Long codigoUnidade) {
		return dao.recuperarSetoresDaUnidade(codigoUnidade);
	}

	public List<Setor> pesquisar(SetorFilter filtro) {
		return dao.pesquisar(PesquisaService.carregaListaParametrosPesquisa(filtro));
	}

	public Setor salvar(Setor setor) {
		try {
			return dao.salvar(setor);
		} catch (Exception e) {
			throw new NegocioException("Erro ao salvar o registro!");
		}
	}

	public Setor excluir(Setor setor) {
		try {
			return dao.excluir(setor.getCodigo());
		} catch (Exception e) {
			throw new NegocioException("Erro ao excluir o registro!");
		}
	}

	public List<Setor> listarSetor() {
		return dao.listarSetor();
	}

	public Setor recuperarSetor(Long codigo) {
		return dao.recuperarSetor(codigo);
	}

	public List<Setor> pesquisarSetor(SetorFilter filtro) {
		return dao.pesquisarSetor(PesquisaService.carregaListaParametrosPesquisa(filtro));
	}

}
