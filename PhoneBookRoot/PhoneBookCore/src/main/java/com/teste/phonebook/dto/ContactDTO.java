package com.teste.phonebook.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.teste.phonebook.entity.Contact;
import com.teste.phonebook.entity.Operator;

public class ContactDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String serial;
	private String name;
	private String phone;
	private LocalDateTime data;
	private int status;
	private OperatorDTO operator;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public OperatorDTO getOperator() {
		return operator;
	}

	public void setOperator(OperatorDTO operator) {
		this.operator = operator;
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
		ContactDTO other = (ContactDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ContactDTO [id=" + id + ", serial=" + serial + ", name=" + name + ", phone=" + phone + ", data=" + data
				+ ", status=" + status + ", operator=" + operator + "]";
	}

	public static Contact toContact(ContactDTO dto) {
		Contact c = new Contact();
		c.setId(dto.getId());
		c.setSerial(dto.getSerial());
		c.setName(dto.getName());
		c.setPhone(dto.getPhone());
		c.setData(dto.getData());
		c.setStatus(dto.getStatus());
		OperatorDTO o = dto.getOperator();
		if (o != null) {
			c.setOperator(OperatorDTO.toOperator(o));
		}

		return c;
	}

	public static ContactDTO fromContact(Contact c) {
		ContactDTO dto = new ContactDTO();
		dto.setId(c.getId());
		dto.setSerial(c.getSerial());
		dto.setName(c.getName());
		dto.setPhone(c.getPhone());
		dto.setData(c.getData());
		dto.setStatus(c.getStatus());
		Operator o = c.getOperator();
		if (o != null) {
			dto.setOperator(OperatorDTO.fromOperator(o));
		}

		return dto;
	}

}
