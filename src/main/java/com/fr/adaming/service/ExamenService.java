package com.fr.adaming.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
	}

	@Override
	public List<Examen> findAll() {
		log.info("Recuperation de la liste des examens");
		return dao.findAll();
	}

	@Override
	public Examen findById(int id) {
		log.info("Recuperation d'un Examen");
		return dao.findById(id).orElse(null);
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
		} catch (NullPointerException ec) {
			log.error("NullPointerException : update d'un objet null");
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
		} catch (IllegalArgumentException e) {
			log.error("ERROR delete exam "+ e.getMessage());
			return false;
		}
	}
	
	@Override
	public List<Examen> listByMatiere(int idMatiere){
		List<Examen> list = new ArrayList<>();
		if (matiereDao.findById(idMatiere).isPresent()) {
			log.info("SUCCESS LIST BY Matiere");
			list = dao.listByMatiere(idMatiere);
			return list;
		}
		return list;
	}

}
