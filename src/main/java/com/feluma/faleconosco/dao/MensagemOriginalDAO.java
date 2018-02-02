package com.feluma.faleconosco.dao;

import java.io.Serializable;

import com.feluma.faleconosco.dao.generic.GenericoDAO;
import com.feluma.faleconosco.model.MensagemOriginal;

public class MensagemOriginalDAO extends GenericoDAO<MensagemOriginal, Long> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected Class<MensagemOriginal> getClasseDaEntidade() {
		return MensagemOriginal.class;
	}

}
