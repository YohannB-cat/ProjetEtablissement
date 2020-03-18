package com.fr.adaming.service;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.INiveauDao;
import com.fr.adaming.entity.Classe;
import com.fr.adaming.entity.Niveau;

@Service("niveauservice")
public class NiveauService implements INiveauService {
	
	@Autowired
	private INiveauDao dao;


	@Override
	public Niveau create(Niveau niveau) {
		try {
			if (niveau == null || dao.existsById(niveau.getId())) {
				return null;
			}
			return dao.save(niveau);
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
	public List<Niveau> findAll() {
		if (dao.findAll().isEmpty()) {
			return null;
		}
		return dao.findAll();
	}

	@Override
	public Niveau findById(int id) {
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
	public boolean update(Niveau niveau) {
		try {
			if (dao.existsById(niveau.getId())) {
				dao.save(niveau);
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

	
	public List<Classe> findListClasseByIdNiveau(int idNiveau) {
		try {
			if(dao.findById(idNiveau) != null && idNiveau!=0) {
				return dao.findListClasseByIdNiveau(idNiveau);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}	
}
