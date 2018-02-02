package com.feluma.faleconosco.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.feluma.faleconosco.dao.AvaliacaoDAO;
import com.feluma.faleconosco.util.relatorio.DescricaoValor;

public class RelatorioService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private AvaliacaoDAO daoAvaliacao;

	public List<DescricaoValor> mensagemPorStatus() {
		return daoAvaliacao.mensagemPorStatus();
	}

	public List<DescricaoValor> mensagemPorSetor() {
		return daoAvaliacao.mensagemPorSetor();
	}

	public List<DescricaoValor> mensagemPorAssunto() {
		return daoAvaliacao.mensagemPorAssunto();
	}	
	
}
