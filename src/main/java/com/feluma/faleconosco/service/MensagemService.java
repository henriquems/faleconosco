package com.feluma.faleconosco.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.feluma.faleconosco.dao.MensagemDAO;
import com.feluma.faleconosco.model.Mensagem;

public class MensagemService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private MensagemDAO dao;
	
	public Mensagem salvar(Mensagem mensagem) {
		try {
			return dao.salvar(mensagem);
		} catch (Exception e) {
			throw new NegocioException("Erro ao salvar a mensagem!");
		}
	}

	public Mensagem recuperarMensagem(Long codigo) {
		return dao.recuperarMensagem(codigo);
	}
	

}
