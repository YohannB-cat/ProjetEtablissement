package com.fr.adaming.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IMatiereDao;
import com.fr.adaming.dao.IModuleDao;
import com.fr.adaming.entity.Matiere;

import lombok.extern.slf4j.Slf4j;

@Service ("matiereservice")
@Slf4j
public class MatiereService implements IMatiereService {

	@Autowired
	private IMatiereDao matDao;
	
	@Autowired
	private IModuleDao modDao;

	@Override
	public Matiere create(Matiere matiere) {
		try {
			if (matiere == null || matDao.existsById(matiere.getId())) {
				return null;
			}
			return matDao.save(matiere);
		} catch (DataIntegrityViolationException e) {
			log.error("MESSAGE ERROR CREATE MATIERE"+ e.getMessage());
			return null;
		}

		catch (ConstraintViolationException er) {
			log.error("MESSAGE ERROR CREATE MATIERE" + er.getMessage());
			return null;
		}
	}

	@Override
	public List<Matiere> findAll() {
		List <Matiere> listeMatiere = new ArrayList<>();
		if (matDao.findAll().isEmpty()) {
			return listeMatiere;
		}
		return matDao.findAll();
	}

	@Override
	public Matiere findById(int id) {
		try {
			if (id != 0) {
				return matDao.findById(id).orElse(null);
			} else {
				return null;
			}
		} catch (InvalidDataAccessApiUsageException e) {
			log.error("ERROR FIND BY ID MATIERE" + e.getMessage());
			return null;
		}
	}

	@Override
	public Boolean update(Matiere matiere) {
		try {
			if (matDao.existsById(matiere.getId())) {
				matDao.save(matiere);
				return true;
			} else {
				return false;
			}
		} catch (InvalidDataAccessApiUsageException er) {
			log.error("ERROR UPDATE MATIERE" + er.getMessage());
			return false;
		} catch (NullPointerException ec) {
			log.error("ERROR UPDATE MATIERE" + ec.getMessage());
			return false;
		}
	}

	@Override
	public boolean deleteById(Integer id) {
		try {
			if (matDao.findById(id).isPresent()  && id != null) {
				matDao.deleteById(id);
				return true;
			} else {
				return false;
			}
		} catch (InvalidDataAccessApiUsageException e) {
			log.error("ERROR DELETE BY ID" + e.getMessage());
			return false;
		} catch (EmptyResultDataAccessException er) {
			log.error("ERRROR DELETE BY ID" + er.getMessage());
			return false;
		}
	}

	@Override
	public List<Matiere> findMatiereByIdModule(Integer matieres) {
		List<Matiere> listeMatiere = new ArrayList<>();
		if (matieres != null) {
			return  matDao.findMatiereByMatieres(matieres);
		}
		else {
			return listeMatiere;
		}
	}
	
	

}
