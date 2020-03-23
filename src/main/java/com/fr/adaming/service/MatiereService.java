package com.fr.adaming.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IMatiereDao;
import com.fr.adaming.entity.Matiere;

import lombok.extern.slf4j.Slf4j;

@Service("matiereservice")
@Slf4j
public class MatiereService implements IMatiereService {

	@Autowired
	private IMatiereDao matDao;

	@Override
	public Matiere create(Matiere matiere) {

		if (matiere == null || matDao.existsById(matiere.getId())) {
			return null;
		} else {
			return matDao.save(matiere);
		}

	}

	@Override
	public List<Matiere> findAll() {
		List<Matiere> listeMatiere = new ArrayList<>();
		if (matDao.findAll().isEmpty()) {
			return listeMatiere;
		}
		return matDao.findAll();
	}

	@Override
	public Matiere findById(int id) {

		if (id != 0) {
			return matDao.findById(id).orElse(null);
		} else {
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
		} catch (Exception er) {
			log.error("ERROR update matiere "+ er.getMessage());
			return false;
		}

	}

	@Override
	public boolean deleteById(Integer id) {

		if (matDao.findById(id).isPresent() && id != null) {
			matDao.deleteById(id);
			return true;
		} else {
			return false;
		}

	}

	@Override
	public List<Matiere> findMatiereByIdModule(Integer matieres) {
		List<Matiere> listeMatiere = new ArrayList<>();
		if (matieres != null) {
			return matDao.findMatiereByMatieres(matieres);
		} else {
			return listeMatiere;
		}
	}

}
