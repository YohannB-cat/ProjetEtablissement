package com.fr.adaming.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.INiveauDao;
import com.fr.adaming.entity.Classe;
import com.fr.adaming.entity.Niveau;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
			log.warn(e.getMessage());
			return null;
		} catch (ConstraintViolationException er) {
			log.warn(er.getMessage());
			return null;
		}
	}

	@Override
	public List<Niveau> findAll() {
		return dao.findAll();
	}

	@Override
	public Niveau findById(int id) {
		log.info("Recuperation d'un Niveau");
		return dao.findById(id).orElse(null);
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
			log.warn(er.getMessage());
			return false;
		} catch (NullPointerException ec) {
			log.warn(ec.getMessage());
			return false;
		}
	}

	@Override
	public boolean deleteById(int id) {
		try {
			Optional<Niveau> n = dao.findById(id);
			if (n.isPresent() && id != 0) {
				dao.deleteById(id);
				return true;
			} else {
				return false;
			}
		} catch (InvalidDataAccessApiUsageException e) {
			log.warn(e.getMessage());
			return false;
		} catch (EmptyResultDataAccessException er) {
			log.warn(er.getMessage());
			return false;
		}
	}

	@Override
	public List<Classe> findListClasseByIdNiveau(int idNiveau) {
		try {
			Optional<Niveau> n = dao.findById(idNiveau);
			if (n.isPresent() && idNiveau != 0) {
				return dao.findListClasseByIdNiveau(idNiveau);}
			
		} catch (Exception e) {
			log.warn(e.getMessage());
			return new ArrayList<>();
		}
		return new ArrayList<>();
	}
}
