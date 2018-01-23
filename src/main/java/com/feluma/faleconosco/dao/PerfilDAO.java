package com.feluma.faleconosco.dao;

import java.io.Serializable;

import com.feluma.faleconosco.dao.generic.GenericoDAO;
import com.feluma.faleconosco.model.Perfil;

public class PerfilDAO extends GenericoDAO<Perfil, Long> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected Class<Perfil> getClasseDaEntidade() {
		return Perfil.class;
	}

	public Perfil recuperarPerfilPorNome(String nome) {
		StringBuffer sb = new StringBuffer();
		sb.append("select per from Perfil per ");
		sb.append("where per.nome = :nome");
		
		return getEntityManager().createQuery(sb.toString(), Perfil.class)
				.setParameter("nome", nome)
				.getSingleResult();
	}

}

