package com.feluma.faleconosco.dao;

import java.io.Serializable;

import com.feluma.faleconosco.dao.generic.GenericoDAO;
import com.feluma.faleconosco.model.Mensagem;

public class MensagemDAO extends GenericoDAO<Mensagem, Long> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected Class<Mensagem> getClasseDaEntidade() {
		return Mensagem.class;
	}

	public Mensagem recuperarMensagem(Long codigo) {
		StringBuffer sb = new StringBuffer();
		sb.append("select men from Mensagem men ");
		sb.append("where men.codigo = :codigo");
		
		return getEntityManager().createQuery(sb.toString(), Mensagem.class)
				.setParameter("codigo", codigo)
				.getSingleResult();
	}

}
