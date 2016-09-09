package com.teste.phonebook.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class PersistenceUtil {

	@PersistenceContext
	@Produces
	private EntityManager em;

}
