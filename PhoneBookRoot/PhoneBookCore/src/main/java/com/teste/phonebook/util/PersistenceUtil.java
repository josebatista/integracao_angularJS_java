package com.teste.phonebook.util;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PersistenceUtil {

	@PersistenceContext
	@Produces
	private EntityManager em;

}
