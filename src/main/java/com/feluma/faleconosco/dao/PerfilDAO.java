package com.feluma.faleconosco.dao;

import java.io.Serializable;

import com.feluma.faleconosco.dao.generic.GenericoDAO;
import com.feluma.faleconosco.model.Perfil;

public class PerfilDAO extends GenericoDAO<Perfil, Long> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected Class<Perfil> getClasseDaEntidade() {
		return Perfil.class;
	}

}

