package com.teste.phonebook.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.UserTransaction;

import com.teste.phonebook.dao.ContactDAO;
import com.teste.phonebook.dto.ContactDTO;
import com.teste.phonebook.entity.Contact;

@ApplicationScoped
public class ContactBO implements Serializable, IContactBO {

	private static final long serialVersionUID = 1L;

	@Inject
	private ContactDAO dao;

	@Inject
	private UserTransaction tx;

	@Override
	public boolean save(ContactDTO c) {
		boolean ret = false;
		try {
			this.tx.begin();
			this.dao.save(ContactDTO.toContact(c));
			this.tx.commit();
			ret = true;
		} catch (Exception e) {
			try {
				ret = false;
				this.tx.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return ret;
	}

	@Override
	public boolean remove(ContactDTO c) {
		c.setStatus(0);
		boolean ret = false;
		try {
			this.tx.begin();
			this.dao.save(ContactDTO.toContact(c));
			this.tx.commit();
			ret = true;
		} catch (Exception e) {
			try {
				ret = false;
				this.tx.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return ret;
	}

	@Override
	public List<ContactDTO> listAll() {
		List<ContactDTO> listDTO = null;
		List<Contact> list = null;
		try {
			this.tx.begin();
			list = this.dao.loadAll();
			if (list.size() > 0) {
				listDTO = new ArrayList<ContactDTO>();
				for (Contact contact : list) {
					listDTO.add(ContactDTO.fromContact(contact));
				}
			}
			this.tx.commit();
		} catch (Exception e) {
			try {
				System.out.println("INICIO LOG");
				e.printStackTrace();
				System.out.println("FIM LOG");
				this.tx.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return listDTO;
	}

	@Override
	public ContactDTO findById(Long id) {
		ContactDTO c = null;
		try {
			this.tx.begin();
			c = ContactDTO.fromContact(this.dao.findById(id));
			this.tx.commit();
		} catch (Exception e) {
			try {
				this.tx.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return c;
	}

}
