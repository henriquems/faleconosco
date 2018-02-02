package com.feluma.faleconosco.dao;

import java.io.Serializable;
import java.util.List;

import com.feluma.faleconosco.dao.generic.GenericoDAO;
import com.feluma.faleconosco.filter.PesquisaFilter;
import com.feluma.faleconosco.model.Setor;
import com.feluma.faleconosco.service.PesquisaService;

public class SetorDAO extends GenericoDAO<Setor, Long> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected Class<Setor> getClasseDaEntidade() {
		return Setor.class;
	}

	public List<Setor> recuperarSetoresDaUnidade(Long codigoUnidade) {
		StringBuffer sb = new StringBuffer();
		sb.append("select seto from Setor seto ");
		sb.append("inner join fetch seto.unidades uni ");
		sb.append("where uni.codigo = :codigoUnidade ");
		sb.append("order by seto.descricao");
		
		return getEntityManager().createQuery(sb.toString(), Setor.class)
				.setParameter("codigoUnidade", codigoUnidade)
				.getResultList();
	}

	public List<Setor> listarSetor() {
		StringBuffer sb = new StringBuffer();
		sb.append("select distinct seto from Setor seto ");
		sb.append("inner join fetch seto.unidades uni ");
		sb.append("order by seto.descricao");
		
		return getEntityManager().createQuery(sb.toString(), Setor.class)
				.getResultList();
	}

	public Setor recuperarSetor(Long codigo) {
		StringBuffer sb = new StringBuffer();
		sb.append("select seto from Setor seto ");
		sb.append("inner join fetch seto.unidades uni ");
		sb.append("where seto.codigo = :codigo");
		
		return getEntityManager().createQuery(sb.toString(), Setor.class)
				.setParameter("codigo", codigo)
				.getSingleResult();
	}

	public List<Setor> pesquisarSetor(List<PesquisaFilter> listaParamentrosPesquisa) {
		StringBuffer sb = new StringBuffer();
		sb.append("select distinct seto from Setor seto ");
		sb.append("inner join fetch seto.unidades uni ");
		sb.append("where seto.codigo <> 0 ");
		
		PesquisaService.testarCamposPesquisaSetor(sb, listaParamentrosPesquisa);
		
		sb.append("order by seto.descricao");
		
		return getEntityManager().createQuery(sb.toString(), Setor.class)
				.getResultList();
	}

}
