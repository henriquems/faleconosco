package com.feluma.faleconosco.dao;

import java.io.Serializable;
import java.util.List;

import com.feluma.faleconosco.dao.generic.GenericoDAO;
import com.feluma.faleconosco.model.UnidadeSetor;

public class UnidadeSetorDAO extends GenericoDAO<UnidadeSetor, Long> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected Class<UnidadeSetor> getClasseDaEntidade() {
		return UnidadeSetor.class;
	}

	public List<UnidadeSetor> recuperarSetoresDaUnidade(Long codigo) {
		StringBuffer sb = new StringBuffer();
		sb.append("select distinct uniSet from UnidadeSetor uniSet ");
		sb.append("inner join fetch uniSet.setor seto ");
		sb.append("where uniSet.unidade.codigo = :codigo ");
		sb.append("order by seto.descricao");
		
		return getEntityManager().createQuery(sb.toString(), UnidadeSetor.class)
				.setParameter("codigo", codigo)
				.getResultList();
	}

	public UnidadeSetor recuperarUnidadeSetor(Long codigoUnidade, Long codigoSetor) {
		StringBuffer sb = new StringBuffer();
		sb.append("select distinct uniSet from UnidadeSetor uniSet ");
		sb.append("where uniSet.unidade.codigo = :codigoUnidade and ");
		sb.append("uniSet.setor.codigo = :codigoSetor");
		
		return getEntityManager().createQuery(sb.toString(), UnidadeSetor.class)
				.setParameter("codigoUnidade", codigoUnidade)
				.setParameter("codigoSetor", codigoSetor)
				.getSingleResult();
	}

}
