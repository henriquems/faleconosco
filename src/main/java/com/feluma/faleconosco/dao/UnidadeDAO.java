package com.feluma.faleconosco.dao;

import java.io.Serializable;

import com.feluma.faleconosco.dao.generic.GenericoDAO;
import com.feluma.faleconosco.model.Unidade;

public class UnidadeDAO extends GenericoDAO<Unidade, Long> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected Class<Unidade> getClasseDaEntidade() {
		return Unidade.class;
	}

}
