package com.teste.phonebook.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.teste.phonebook.entity.Contact;
import com.teste.phonebook.entity.Operator;

public class OperatorDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private int code;
	private String category;
	private double value;
	private List<ContactDTO> contacts;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public List<ContactDTO> getContacts() {
		return contacts;
	}

	public void setContacts(List<ContactDTO> contacts) {
		this.contacts = contacts;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OperatorDTO other = (OperatorDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OperatorDTO [id=" + id + ", name=" + name + ", code=" + code + ", category=" + category + ", value="
				+ value + ", contacts=" + contacts + "]";
	}

	public static Operator toOperator(OperatorDTO dto) {
		Operator o = new Operator();
		o.setId(dto.getId());
		o.setName(dto.getName());
		o.setCode(dto.getCode());
		o.setCategory(dto.getCategory());
		o.setValue(dto.getValue());
		List<ContactDTO> listContactDTO = dto.getContacts();
		if (listContactDTO != null) {
			List<Contact> listContact = new ArrayList<Contact>();
			for (ContactDTO contactDTO : listContactDTO) {
				listContact.add(ContactDTO.toContact(contactDTO));
			}
			o.setContacts(listContact);
		}

		return o;
	}

	public static OperatorDTO fromOperator(Operator o) {
		OperatorDTO dto = new OperatorDTO();
		dto.setId(o.getId());
		dto.setName(o.getName());
		dto.setCode(o.getCode());
		dto.setCategory(o.getCategory());
		dto.setValue(o.getValue());
		List<Contact> listContact = o.getContacts();
		if (listContact != null) {
			List<ContactDTO> listContactDTO = new ArrayList<ContactDTO>();
			for (Contact contact : listContact) {
				listContactDTO.add(ContactDTO.fromContact(contact));
			}
			dto.setContacts(listContactDTO);
		}

		return dto;
	}

}
