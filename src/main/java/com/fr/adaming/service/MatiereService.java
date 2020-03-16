package com.fr.adaming.service;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import com.fr.adaming.Dao.IMatiereDao;
import com.fr.adaming.entity.Matiere;

public class MatiereService implements IMatiereService {
	
	@Autowired
	private IMatiereDao dao;

	@Override
	public Matiere create(Matiere matiere) {
		try {
			if (matiere == null || dao.existsById(matiere.getId())) {
				return null;
			}
			return dao.save(matiere);
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
	public List<Matiere> findAll() {
		if (dao.findAll().isEmpty()) {
			return null;
		}
		return dao.findAll();
	}

	@Override
	public Matiere findById(int id) {
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
	public Matiere findByNom(String nom) {
		if (nom != null) {
			return dao.findByNom(nom);
		} else {
			return null;
		}
	}

	@Override
	public boolean update(Matiere matiere) {
		try {
			if (dao.existsById(matiere.getId())) {
				dao.save(matiere);
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

	@Override
	public boolean deleteByNom(String nom) {
		// TODO Auto-generated method stub
		return false;
	}

}
