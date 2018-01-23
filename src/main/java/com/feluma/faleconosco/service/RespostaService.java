package com.feluma.faleconosco.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.feluma.faleconosco.dao.RespostaDAO;
import com.feluma.faleconosco.model.Resposta;

public class RespostaService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private RespostaDAO dao;
	
	public Resposta salvar(Resposta resposta){
		try {
			return dao.salvar(resposta);
		} catch (Exception e) {
			throw new NegocioException("Erro ao salvar o registro!");
		}
	}

	public Resposta recuperarResposta(Long codigo) {
		return dao.recuperarResposta(codigo);
	}

}
