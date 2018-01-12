package com.feluma.faleconosco.util.jpa;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequestScoped
public class EntityManagerProducer {
	
	@PersistenceContext(unitName="faleconoscoPU")
	private EntityManager manager;
	
	public EntityManagerProducer(){}
	
	@Produces @RequestScoped
	public EntityManager createEntityManager(){
		return manager;
	}
}
