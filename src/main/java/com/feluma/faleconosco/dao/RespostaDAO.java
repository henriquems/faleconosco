package com.feluma.faleconosco.dao;

import java.io.Serializable;

import com.feluma.faleconosco.dao.generic.GenericoDAO;
import com.feluma.faleconosco.model.Resposta;

public class RespostaDAO extends GenericoDAO<Resposta, Long> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected Class<Resposta> getClasseDaEntidade() {
		return Resposta.class;
	}

	public Resposta recuperarResposta(Long codigo) {
		StringBuffer sb = new StringBuffer();
		sb.append("select res from Resposta res ");
		sb.append("inner join fetch res.mensagem men ");
		sb.append("inner join fetch men.cliente cli ");
		sb.append("inner join fetch res.usuario usu ");
		sb.append("inner join fetch usu.unidadeSetor uniSet ");
		sb.append("inner join fetch uniSet.id.unidade uni ");
		sb.append("inner join fetch uniSet.id.setor seto ");
		sb.append("where res.codigo = :codigo");
		
		return getEntityManager().createQuery(sb.toString(), Resposta.class)
				.setParameter("codigo", codigo)
				.getSingleResult();
	}

}
