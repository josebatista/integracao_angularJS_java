package com.teste.phonebook.dao;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.teste.phonebook.entity.Operator;

@ApplicationScoped
public class OperatorDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public List<Operator> loadAll() throws Exception {
		return this.em.createQuery("SELECT o FROM Operator o", Operator.class).getResultList();
	}

	public Operator findById(Long id) throws Exception {
		return this.em.find(Operator.class, id);
	}

	public void save(Operator o) throws Exception {
		if (o == null) {
			throw new Exception("Contact is not present");
		}
		if (o.getId() == null) {
			this.em.persist(o);
		} else {
			this.em.merge(o);
		}
	}

}
