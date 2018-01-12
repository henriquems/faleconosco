package com.feluma.faleconosco.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.feluma.faleconosco.dao.PerfilDAO;
import com.feluma.faleconosco.model.Perfil;

public class PerfilService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private PerfilDAO dao;
	
	public List<Perfil> listar(){
		return dao.listar();
	}

	public Perfil pesquisarPorCodigo(Long codigo) {
		return dao.pesquisarPorCodigo(codigo);
	}

}
