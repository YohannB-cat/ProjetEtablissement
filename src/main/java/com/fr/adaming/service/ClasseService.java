package com.fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import com.fr.adaming.dao.IClasseDao;
import com.fr.adaming.entity.Classe;

public class ClasseService implements IClasseService{
	
	@Autowired
	private IClasseDao dao;

	@Override
	public Classe create(Classe classe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Classe> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Classe findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Classe classe) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(int id) {
		try {
			if (dao.findById(id) != null && id != 0) {
				dao.deleteById(id);
				return true;
			} else {
				return false;
			}
		} catch (InvalidDataAccessApiUsageException e) {
			e.printStackTrace();
			return false;
		} catch (EmptyResultDataAccessException er) {
			er.printStackTrace();
			return false;
		}
	}

}
