package com.teste.phonebook.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.teste.phonebook.entity.Contact;

public class ContactDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	public List<Contact> loadAll() throws Exception {
		return this.em.createQuery("SELECT c FROM Contact c", Contact.class).getResultList();
	}

	public Contact findById(Long id) throws Exception {
		return this.em.find(Contact.class, id);
	}

	public void save(Contact c) throws Exception {
		if (c == null) {
			throw new Exception("Contact is not present");
		}
		if (c.getId() == null) {
			this.em.persist(c);
		} else {
			this.em.merge(c);
		}
	}

}
