package com.feluma.faleconosco.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

import com.feluma.faleconosco.dao.generic.GenericoDAO;
import com.feluma.faleconosco.filter.PesquisaFilter;
import com.feluma.faleconosco.model.Perfil;
import com.feluma.faleconosco.model.Usuario;
import com.feluma.faleconosco.service.PesquisaService;

public class UsuarioDAO extends GenericoDAO<Usuario, Long> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Resource
	private UserTransaction transaction;
	
	protected Class<Usuario> getClasseDaEntidade() {
		return Usuario.class;
	}
	
	public Usuario logar(String email) {
		Usuario usuario = null;
		
		StringBuffer sb = new StringBuffer();
		sb.append("select u from Usuario u ");
		sb.append("inner join fetch u.perfis p ");
		sb.append("where u.email like :email");
		
		try {
			usuario = getEntityManager().createQuery(sb.toString(), Usuario.class)
					.setParameter("email", ""+email+"%")
					.getSingleResult();
		} catch (Exception e) {}
		
		return usuario;
	}

	public Usuario recuperarUsuario(Long codigo) {
		StringBuffer sb = new StringBuffer();
		sb.append("select distinct usu from Usuario usu ");
		sb.append("inner join fetch usu.perfis per ");
		sb.append("inner join fetch usu.unidadeSetor uniSet ");
		sb.append("inner join fetch uniSet.unidade uni ");
		sb.append("inner join fetch uniSet.setor seto ");
		sb.append("where usu.codigo = :codigo");
		
		return getEntityManager().createQuery(sb.toString(), Usuario.class)
				.setParameter("codigo", codigo)
				.getSingleResult();
	}
		
	@SuppressWarnings("unused")
	public Usuario buscarPerfisDoUsuario(Long codigoUsuario) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);
		Root<Usuario> usuario = criteriaQuery.from(Usuario.class);
	
		Fetch<Usuario, Perfil> perfis = usuario.fetch("perfis", JoinType.INNER);
			
		criteriaQuery.where(criteriaBuilder.equal(usuario.get("codigo"), codigoUsuario));
		
		return getEntityManager().createQuery(criteriaQuery).getSingleResult();
	}

	public Long verificarCPF(String cpf) {
		return getEntityManager().createQuery("select count(u) from Usuario u where u.cpf = :cpf", Long.class)
				.setParameter("cpf", cpf)
				.getSingleResult();
	}
	
	public Long verificarEmail(String email) {
		return getEntityManager().createQuery("select count(u) from Usuario u where u.email = :email", Long.class)
				.setParameter("email", email)
				.getSingleResult();
	}

	public void restaurarSenha(Usuario usuario) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("update usuario set sen_usu = :senha where cod_usu = :codigoUsuario");
		
		try {
			transaction.begin();
			Query query = getEntityManager().createNativeQuery(sb.toString())
					.setParameter("senha", usuario.getSenha())
					.setParameter("codigoUsuario", usuario.getCodigo());
			query.executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw new Exception();
		}
	}

	public Usuario recuperarUsuarioPorCpf(String cpf) {
		Usuario usuario = null;
		
		try {
			usuario = getEntityManager().createQuery("select u from Usuario u where u.cpf = :cpf", Usuario.class)
					.setParameter("cpf", cpf)
					.getSingleResult();
		} catch (Exception e) {}
		
		return usuario;
	}

	public List<Usuario> listarUsuario() {
		StringBuffer sb = new StringBuffer();
		sb.append("select distinct usu from Usuario usu ");
		sb.append("inner join fetch usu.perfis per ");
		sb.append("inner join fetch usu.unidadeSetor uniSet ");
		sb.append("inner join fetch uniSet.unidade uni ");
		sb.append("inner join fetch uniSet.setor seto ");
		sb.append("order by usu.nome");
		
		return getEntityManager().createQuery(sb.toString(), Usuario.class)
				.getResultList();
	}

	public List<Usuario> pesquisarUsuario(List<PesquisaFilter> listaParamentrosPesquisa) {
		StringBuffer sb = new StringBuffer();
		sb.append("select distinct usu from Usuario usu ");
		sb.append("inner join fetch usu.perfis per ");
		sb.append("inner join fetch usu.unidadeSetor uniSet ");
		sb.append("inner join fetch uniSet.unidade uni ");
		sb.append("inner join fetch uniSet.setor seto ");
		sb.append("where usu.codigo <> 0 ");
		
		PesquisaService.testarCamposPesquisaUsuario(sb, listaParamentrosPesquisa);
		
		sb.append("order by usu.nome");
		
		return getEntityManager().createQuery(sb.toString(), Usuario.class)
				.getResultList();
	}

}