package com.fr.adaming.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IEtudiantDao;
import com.fr.adaming.entity.Etudiant;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("etudiantservice")
public class EtudiantService implements IEtudiantService {

	@Autowired
	private IEtudiantDao dao;

	@Override
	public Etudiant create(Etudiant etudiant) {
		try {
			if (etudiant == null || dao.existsById(etudiant.getId())) {
				return null;
			}
			return dao.save(etudiant);
		} catch (Exception e) {
			log.warn(e.getMessage());
			return null;
		}
	}

	@Override
	public List<Etudiant> findAll() {
		if (dao.findAll().isEmpty()) {
			return new ArrayList<>();
		}
		return dao.findAll();
	}

	@Override
	public Etudiant findById(int id) {
		try {
			if (id != 0) {
				return dao.findById(id).orElse(null);
			} else {
				return null;
			}
		} catch (Exception e) {
			log.warn(e.getMessage());
			return null;
		}
	}

	@Override
	public boolean update(Etudiant etudiant) {
		try {
			if (dao.existsById(etudiant.getId())) {
				dao.save(etudiant);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			log.warn(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean deleteById(int id) {
		try {
			if (dao.existsById(id) && id != 0) {
				dao.deleteById(id);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			log.warn(e.getMessage());
			return false;
		}
	}

}
