package com.feluma.faleconosco.dao;

import java.io.Serializable;

import com.feluma.faleconosco.dao.generic.GenericoDAO;
import com.feluma.faleconosco.model.Cliente;

public class ClienteDAO extends GenericoDAO<Cliente, Long> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected Class<Cliente> getClasseDaEntidade() {
		return Cliente.class;
	}

}
