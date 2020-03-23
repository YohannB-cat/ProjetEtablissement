package com.fr.adaming.service;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IClasseDao;
import com.fr.adaming.entity.Classe;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
		} catch (DataIntegrityViolationException ep) {
			log.warn(ep.getMessage());
			return null;
		}

		catch (ConstraintViolationException er) {
			log.warn("MESSAGE ERROR CREATE MATIERE" + er.getMessage());
			return null;
		}
	}

	@Override
	public List<Classe> findAll() {
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
			log.warn(e.getMessage());
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
		} catch (InvalidDataAccessApiUsageException ex) {
			log.warn(ex.getMessage());
			return false;
		} catch (NullPointerException ec) {
			log.warn(ec.getMessage());
			return false;
		}
	}

	@Override
	public boolean deleteById(int id) {
		try {
			Optional<Classe> c = dao.findById(id);
			if (c.isPresent()) {
				dao.deleteById(id);
				return true;
			} else {
				return false;
			}
		} catch (InvalidDataAccessApiUsageException ef) {
			log.warn(ef.getMessage());
			return false;
		} catch (EmptyResultDataAccessException er) {
			log.warn(er.getMessage());
			return false;
		}
	}
}
