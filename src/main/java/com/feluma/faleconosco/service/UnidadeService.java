package com.feluma.faleconosco.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.feluma.faleconosco.dao.UnidadeDAO;
import com.feluma.faleconosco.model.Unidade;

public class UnidadeService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UnidadeDAO dao;

	public List<Unidade> listar() {
		return dao.listar();
	}
	
}
