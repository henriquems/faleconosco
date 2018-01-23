package com.feluma.faleconosco.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import com.feluma.faleconosco.dao.generic.GenericoDAO;
import com.feluma.faleconosco.filter.PesquisaFilter;
import com.feluma.faleconosco.model.Avaliacao;
import com.feluma.faleconosco.model.StatusAvaliacao;
import com.feluma.faleconosco.model.UnidadeSetor;
import com.feluma.faleconosco.service.PesquisaService;
import com.feluma.faleconosco.util.relatorio.DescricaoValor;

public class AvaliacaoDAO extends GenericoDAO<Avaliacao, Long> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Resource
	private UserTransaction transaction;
	
	protected Class<Avaliacao> getClasseDaEntidade() {
		return Avaliacao.class;
	}

	public List<Avaliacao> listarAvaliacao(StatusAvaliacao status) {
		StringBuffer sb = new StringBuffer();
		sb.append("select distinct ava from Avaliacao ava ");
		sb.append("inner join fetch ava.mensagem men ");
		sb.append("inner join fetch men.cliente cli ");
		sb.append("where ava.statusAvaliacao = :status ");
		sb.append("order by ava.data desc");
		
		return getEntityManager().createQuery(sb.toString(), Avaliacao.class)
				.setParameter("status", status)
				.getResultList();
	}

	public List<Avaliacao> listarAvaliacao() {
		StringBuffer sb = new StringBuffer();
		sb.append("select distinct ava from Avaliacao ava ");
		sb.append("inner join fetch ava.mensagem men ");
		sb.append("inner join fetch men.cliente cli ");
		sb.append("order by ava.statusAvaliacao, ava.data desc");
		
		return getEntityManager().createQuery(sb.toString(), Avaliacao.class)
				.getResultList();
	}

	public Avaliacao recuperarAvaliacao(Long codigo) {
		StringBuffer sb = new StringBuffer();
		sb.append("select ava from Avaliacao ava ");
		sb.append("inner join fetch ava.mensagem men ");
		sb.append("inner join fetch men.cliente cli ");
		sb.append("left join fetch men.respostas res ");
		sb.append("left join fetch res.usuario usu ");
		sb.append("left join fetch ava.unidadeSetor uniSet ");
		sb.append("left join fetch uniSet.id.unidade uni ");
		sb.append("left join fetch uniSet.id.setor seto ");
		sb.append("where ava.codigo = :codigo");
		
		return getEntityManager().createQuery(sb.toString(), Avaliacao.class)
				.setParameter("codigo", codigo)
				.getSingleResult();
	}

	public List<Avaliacao> pesquisarAvaliacao(List<PesquisaFilter> listaParamentrosPesquisa) {
		StringBuffer sb = new StringBuffer();
		sb.append("select distinct ava from Avaliacao ava ");
		sb.append("inner join fetch ava.mensagem men ");
		sb.append("inner join fetch men.cliente cli ");
		sb.append("where ava.codigo <> 0 ");
		
		PesquisaService.testarCamposPesquisaAvaliacao(sb, listaParamentrosPesquisa);
		
		sb.append("order by ava.statusAvaliacao, ava.data desc");
		
		return getEntityManager().createQuery(sb.toString(), Avaliacao.class)
				.getResultList();
	}

	public void atualizarAvaliacaoAnterior(Long codigoAvaliacao, Long codigoMensagem) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("update avaliacao set flg_ati = 0 where flg_ati = 1 and cod_men = :codigoMensagem and id_ava <> :codigoAvaliacao");
		
		try {
			this.transaction.begin();
			Query query = getEntityManager().createNativeQuery(sb.toString())
					.setParameter("codigoMensagem", codigoMensagem)
					.setParameter("codigoAvaliacao", codigoAvaliacao);
			query.executeUpdate();
			this.transaction.commit();
		} catch (Exception e) {
			this.transaction.rollback();
			throw new Exception();
		}
	}

	public List<DescricaoValor> mensagemPorStatus() {
		StringBuffer sb = new StringBuffer();
		sb.append("select ava.sta_ava, count(ava.id_ava) from avaliacao as ava ");
		sb.append("where ava.flg_ati = 1 group by ava.sta_ava");
		
		Query query = getEntityManager().createNativeQuery(sb.toString());		
		
		@SuppressWarnings("unchecked")
		List<Object[]> lista = query.getResultList();
		
		List<DescricaoValor> valores = new ArrayList<DescricaoValor>();
		
		for(Object[] linha : lista){
			DescricaoValor descricaoValor = new DescricaoValor();
			descricaoValor.setDescricao(linha[0].toString());
			descricaoValor.setValor(Long.valueOf(linha[1].toString()));
			
			valores.add(descricaoValor);
		}
		
		return valores;
	}

	public List<DescricaoValor> mensagemPorSetor() {
		StringBuffer sb = new StringBuffer();
		sb.append("select uni.des_uni, seto.des_set, count(ava.id_ava) from avaliacao as ava ");
		sb.append("join unidade_setor as uniSet on (ava.cod_uni = uniSet.cod_uni and ava.cod_set = uniSet.cod_set) ");
		sb.append("join setor as seto on (uniSet.cod_set = seto.cod_set) ");
		sb.append("join unidade as uni on (uniSet.cod_uni = uni.cod_uni) ");
		sb.append("where ava.flg_ati = 1 group by uni.des_uni, seto.des_set ");
		
		Query query = getEntityManager().createNativeQuery(sb.toString());		
		
		@SuppressWarnings("unchecked")
		List<Object[]> lista = query.getResultList();
		
		List<DescricaoValor> valores = new ArrayList<DescricaoValor>();
		
		for(Object[] linha : lista){
			DescricaoValor descricaoValor = new DescricaoValor();
			descricaoValor.setDescricao(linha[0].toString()+"-"+linha[1].toString());
			descricaoValor.setValor(Long.valueOf(linha[2].toString()));
			
			valores.add(descricaoValor);
		}
		
		return valores;
	}
	
	public List<DescricaoValor> mensagemPorAssunto() {
		StringBuffer sb = new StringBuffer();
		sb.append("select men.ass_men, count(men.cod_men) from mensagem men ");
		sb.append("group by men.ass_men");
		
		Query query = getEntityManager().createNativeQuery(sb.toString());		
		
		@SuppressWarnings("unchecked")
		List<Object[]> lista = query.getResultList();
		
		List<DescricaoValor> valores = new ArrayList<DescricaoValor>();
		
		for(Object[] linha : lista){
			DescricaoValor descricaoValor = new DescricaoValor();
			descricaoValor.setDescricao(linha[0].toString());
			descricaoValor.setValor(Long.valueOf(linha[1].toString()));
			
			valores.add(descricaoValor);
		}
		
		return valores;
	}

	public List<Avaliacao> listarAvaliacaoParaOuvidor() {
		StringBuffer sb = new StringBuffer();
		sb.append("select distinct ava from Avaliacao ava ");
		sb.append("inner join fetch ava.mensagem men ");
		sb.append("inner join fetch men.cliente cli ");
		sb.append("where ava.flagAtivo = 1 and ");
		sb.append("ava.statusAvaliacao in ('ENVIADOPELOCLIENTE','RESPONDIDOPELOGERENTE', 'REJEITADOPELOGERENTE') ");
		sb.append("order by ava.data desc");
		
		return getEntityManager().createQuery(sb.toString(), Avaliacao.class)
				.getResultList();
	}

	public List<Avaliacao> listarAvaliacaoParaGerente(UnidadeSetor unidadeSetor) {
		StringBuffer sb = new StringBuffer();
		sb.append("select distinct ava from Avaliacao ava ");
		sb.append("inner join fetch ava.mensagem men ");
		sb.append("inner join fetch men.cliente cli ");
		sb.append("inner join fetch ava.unidadeSetor uniSet ");
		sb.append("inner join fetch uniSet.id.unidade uni ");
		sb.append("inner join fetch uniSet.id.setor seto ");
		sb.append("where ava.flagAtivo = 1 and ava.statusAvaliacao = 'AVALIADOPELOOUVIDOR' ");
		sb.append("and uniSet.id.unidade.codigo = :codigoUnidade and ");
		sb.append("uniSet.id.setor.codigo = :codigoSetor ");
		sb.append("order by ava.data desc");
		
		return getEntityManager().createQuery(sb.toString(), Avaliacao.class)
				.setParameter("codigoUnidade", unidadeSetor.getId().getUnidade().getCodigo())
				.setParameter("codigoSetor", unidadeSetor.getId().getSetor().getCodigo())
				.getResultList();
	}

}
