package com.teste.phonebook.bo;

import java.util.List;

import com.teste.phonebook.dto.ContactDTO;

public interface IContactBO {
	public boolean save(ContactDTO c);

	public boolean remove(ContactDTO c);

	public List<ContactDTO> listAll();

	public ContactDTO findById(Long id);
}
