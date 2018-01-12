package com.feluma.faleconosco.dao.generic;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.feluma.faleconosco.filter.PesquisaFilter;

public abstract class GenericoDAO<T, ID extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject @Default
	private EntityManager manager;
	
	@Resource
	private UserTransaction transaction;
	
	
	protected abstract Class<T> getClasseDaEntidade();
	
	protected EntityManager getEntityManager() {
		return manager;
	}
	
	protected Session getSession() {
		return manager.unwrap(Session.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> listar(){
		return manager.createQuery("select o from "+getClasseDaEntidade().getSimpleName()+" o order by o.codigo desc")
				.getResultList();
	}
	
	public T pesquisarPorCodigo(ID id) {
		return manager.find(getClasseDaEntidade(), id);
	}
	
	public T salvar(T entidade) throws Exception  {
		T object = null;
		try {
			transaction.begin();
			object = manager.merge(entidade);
			transaction.commit();
			return object;
        } catch (Exception e) {
        	transaction.rollback();
        	throw new Exception();
        }
	}
	
	public T excluir(ID id) throws Exception {
		try {
			transaction.begin();
			T object = manager.find(getClasseDaEntidade(), id);
			manager.remove(object);
			transaction.commit();
			return object;
		} catch (Exception e) {
			transaction.rollback();
			throw new Exception();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<T> pesquisar(List<PesquisaFilter> listaParamentrosPesquisa){
        Session session = manager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(getClasseDaEntidade());
        
        for(PesquisaFilter objPesquisa : listaParamentrosPesquisa){
        	if(objPesquisa.getOperacao().equalsIgnoreCase("like")){
				criteria.add(Restrictions.like(objPesquisa.getCampo(), objPesquisa.getValor()+"%"));
			}
        	if(objPesquisa.getOperacao().equalsIgnoreCase("=")){
				criteria.add(Restrictions.eq(objPesquisa.getCampo(), objPesquisa.getValor()));
			}
        	if(objPesquisa.getOperacao().equalsIgnoreCase(">")){
				criteria.add(Restrictions.gt(objPesquisa.getCampo(), objPesquisa.getValor()));
			}
        	if(objPesquisa.getOperacao().equalsIgnoreCase("<")){
				criteria.add(Restrictions.lt(objPesquisa.getCampo(), objPesquisa.getValor()));
			}
        	if(objPesquisa.getOperacao().equalsIgnoreCase(">=")){
        		criteria.add(Restrictions.ge(objPesquisa.getCampo(), objPesquisa.getValor()));
        	}
        	if(objPesquisa.getOperacao().equalsIgnoreCase("<=")){
        		criteria.add(Restrictions.le(objPesquisa.getCampo(), objPesquisa.getValor()));
        	}
        }
        
        return criteria.list();
    }
	
	
}
