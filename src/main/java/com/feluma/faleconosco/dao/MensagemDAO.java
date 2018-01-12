package com.feluma.faleconosco.dao;

import java.io.Serializable;

import com.feluma.faleconosco.dao.generic.GenericoDAO;
import com.feluma.faleconosco.model.Mensagem;

public class MensagemDAO extends GenericoDAO<Mensagem, Long> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected Class<Mensagem> getClasseDaEntidade() {
		return Mensagem.class;
	}

}
