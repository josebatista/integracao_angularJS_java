package com.teste.phonebook.bo;

import java.util.List;

import com.teste.phonebook.dto.OperatorDTO;

public interface IOperatorBO {
	public boolean save(OperatorDTO o);

	public List<OperatorDTO> listAll();

	public OperatorDTO findById(Long id);
}
