package com.fr.adaming.service;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IClasseDao;
import com.fr.adaming.entity.Classe;

@Service ("classeservice")
public class ClasseService implements IClasseService{
	
	@Autowired
	private IClasseDao dao;

	@Override
	public Classe create(Classe classe) {
		try {
			if (classe == null || dao.existsById(classe.getId())) {
				return null;
			}
			return dao.save(classe);
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			return null;
		}

		catch (ConstraintViolationException er) {
			er.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Classe> findAll() {
		if (dao.findAll().isEmpty()) {
			return null;
		}
		return dao.findAll();
	}

	@Override
	public Classe findById(int id) {
		try {
			if (id != 0) {
				return dao.findById(id).orElse(null);
			} else {
				return null;
			}
		} catch (InvalidDataAccessApiUsageException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean update(Classe classe) {
		try {
			if (dao.existsById(classe.getId())) {
				dao.save(classe);
				return true;
			} else {
				return false;
			}
		} catch (InvalidDataAccessApiUsageException er) {
			er.printStackTrace();
			return false;
		} catch (NullPointerException ec) {
			ec.printStackTrace();
			return false;
		}
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
