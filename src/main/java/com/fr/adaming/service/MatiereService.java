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

@Service ("matiereservice")
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
			e.printStackTrace();
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
			er.printStackTrace();
			return false;
		} catch (NullPointerException ec) {
			ec.printStackTrace();
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
			e.printStackTrace();
			return false;
		} catch (EmptyResultDataAccessException er) {
			er.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Matiere> findMatiereByIdModule(Integer matieres) {
		List<Matiere> listeMatiere = new ArrayList<>();
		if (matieres != 0) {
			listeMatiere =  matDao.findMatiereByMatieres(matieres);
			return listeMatiere;
		}
		else {
			return listeMatiere;
		}
	}
	
	

}
