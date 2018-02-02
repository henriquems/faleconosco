package com.feluma.faleconosco.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.feluma.faleconosco.dao.MensagemOriginalDAO;
import com.feluma.faleconosco.model.MensagemOriginal;

public class MensagemOriginalService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private MensagemOriginalDAO dao;

	public MensagemOriginal salvar(MensagemOriginal mensagemOriginal) {
		try {
			return dao.salvar(mensagemOriginal);
		} catch (Exception e) {
			throw new NegocioException("Erro ao salvar a mensagem!");
		}
	}
	
}
