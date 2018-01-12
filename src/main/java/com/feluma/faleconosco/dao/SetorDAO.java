package com.feluma.faleconosco.dao;

import java.io.Serializable;

import com.feluma.faleconosco.dao.generic.GenericoDAO;
import com.feluma.faleconosco.model.Setor;

public class SetorDAO extends GenericoDAO<Setor, Long> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected Class<Setor> getClasseDaEntidade() {
		return Setor.class;
	}

}
