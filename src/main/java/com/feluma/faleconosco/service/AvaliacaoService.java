package com.feluma.faleconosco.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.feluma.faleconosco.dao.AvaliacaoDAO;
import com.feluma.faleconosco.filter.AvaliacaoFilter;
import com.feluma.faleconosco.model.Avaliacao;
import com.feluma.faleconosco.model.StatusAvaliacao;
import com.feluma.faleconosco.model.UnidadeSetor;

public class AvaliacaoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private AvaliacaoDAO dao;
	
	public Avaliacao salvar(Avaliacao avaliacao){
		try {
			return dao.salvar(avaliacao);
		} catch (Exception e) {
			throw new NegocioException("Erro ao salvar o registro!");
		}
	}

	public List<Avaliacao> listarAvaliacao(StatusAvaliacao status) {
		return dao.listarAvaliacao(status);
	}

	public List<Avaliacao> listarAvaliacao() {
		return dao.listarAvaliacao();
	}

	public List<Avaliacao> pesquisarAvaliacao(AvaliacaoFilter filtro) {
		return dao.pesquisarAvaliacao(PesquisaService.carregaListaParametrosPesquisa(filtro));
	}

	public void atualizarAvaliacaoAnterior(Long codigoAvaliacao, Long codigoMensagem) {
		try {
			dao.atualizarAvaliacaoAnterior(codigoAvaliacao, codigoMensagem);
		} catch (Exception e) {
			throw new NegocioException("Erro ao salvar o registro!");
		}		
	}

	public Avaliacao recuperarAvaliacao(Long codigo) {
		return dao.recuperarAvaliacao(codigo);
	}

	public List<Avaliacao> listarAvaliacaoParaOuvidor() {
		return dao.listarAvaliacaoParaOuvidor();
	}

	public List<Avaliacao> listarAvaliacaoParaGerente(UnidadeSetor unidadeSetor) {
		return dao.listarAvaliacaoParaGerente(unidadeSetor);
	}
	
}
