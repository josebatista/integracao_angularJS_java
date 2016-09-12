package com.teste.phonebook.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.UserTransaction;

import com.teste.phonebook.dao.OperatorDAO;
import com.teste.phonebook.dto.OperatorDTO;
import com.teste.phonebook.entity.Operator;

@ApplicationScoped
public class OperatorBO implements Serializable, IOperatorBO {

	private static final long serialVersionUID = 1L;

	@Inject
	private OperatorDAO dao;

	@Inject
	private UserTransaction tx;

	@Override
	public boolean save(OperatorDTO o) {
		boolean ret = false;
		try {
			this.tx.begin();
			this.dao.save(OperatorDTO.toOperator(o));
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
	public List<OperatorDTO> listAll() {
		try {
			this.tx.begin();
			List<Operator> list = this.dao.loadAll();
			List<OperatorDTO> listDTO = null;
			if (list.size() > 0) {
				listDTO = new ArrayList<OperatorDTO>();
				for (Operator operator : list) {
					listDTO.add(OperatorDTO.fromOperator(operator));
				}
			}
			this.tx.commit();
			return listDTO;
		} catch (Exception e) {
			try {
				this.tx.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public OperatorDTO findById(Long id) {
		OperatorDTO o = null;
		try {
			this.tx.begin();
			o = OperatorDTO.fromOperator(this.dao.findById(id));
			this.tx.commit();
		} catch (Exception e) {
			try {
				this.tx.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return o;
	}

}
