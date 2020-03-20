package com.fr.adaming.service;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IExamenDao;
import com.fr.adaming.dao.IMatiereDao;
import com.fr.adaming.entity.Examen;

import lombok.extern.slf4j.Slf4j;

@Service ("examenservice")
@Slf4j
public class ExamenService implements IExamenService {
	
	@Autowired
	private IExamenDao dao;
	
	@Autowired
	private IMatiereDao matiereDao;

	@Override
	public Examen create(Examen exam) {
		try {
			if (exam == null || dao.existsById(exam.getId())) {
				return null;
			}
			log.info("Creation d'un examen");
			return dao.save(exam);
		} catch (DataIntegrityViolationException e) {
			log.error("DataIntegrityViolationException");
			return null;
		}

		catch (ConstraintViolationException er) {
			log.error("ConstraintViolationException");
			return null;
		}
	}

	@Override
	public List<Examen> findAll() {
		log.info("Recuperation de la liste des examens");
		return dao.findAll();
	}

	@Override
	public Examen findById(int id) {
		try {
			if (id != 0) {
				log.info("Recuperation d'un Examen");
				return dao.findById(id).orElse(null);
			} else {
				log.warn("Tentative de recuperation d'un examen par son id");
				return null;
			}
		} catch (InvalidDataAccessApiUsageException e) {
			log.error("InvalidDataAccessApiUsageException");
			return null;
		}
	}

	@Override
	public boolean update(Examen exam) {
		try {
			if (dao.existsById(exam.getId())) {
				dao.save(exam);
				log.info("SUCCESS update exam");
				return true;
			} else {
				log.warn("FAIL update exam");
				return false;
			}
		} catch (InvalidDataAccessApiUsageException er) {
			log.error("ERROR update exam "+ er.getMessage());
			return false;
		} catch (NullPointerException ec) {
			log.error("ERROR update exam "+ec.getMessage());
			return false;
		}
	}

	@Override
	public boolean deleteById(int id) {
		try {
			if (dao.findById(id).isPresent()) {
				dao.deleteById(id);
				log.info("SUCCESS delete exam");
				return true;
			} else {
				log.warn("FAIL delete exam");
				return false;
			}
		} catch (InvalidDataAccessApiUsageException e) {
			log.error("ERROR delete exam "+ e.getMessage());
			return false;
		} catch (EmptyResultDataAccessException er) {
			log.error("ERROR delete exam"+er.getMessage());
			return false;
		}
	}
	
	@Override
	public List<Examen> listByMatiere(int idMatiere){
		List<Examen> list =null;
		try {
			if (matiereDao.findById(idMatiere).isPresent()) {
				log.info("SUCCESS LIST BY Matiere");
				return list = dao.listByMatiere(idMatiere);
			}
		}catch (InvalidDataAccessApiUsageException e) {
			log.error("ERROR list by Marieres" + e.getMessage());
		} catch (EmptyResultDataAccessException er) {
			log.error("ERROR list By Matiere" +er.getMessage());
		}
		return list;
	}

}
