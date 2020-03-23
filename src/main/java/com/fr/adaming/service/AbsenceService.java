package com.fr.adaming.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IAbsenceDao;
import com.fr.adaming.entity.Absence;

import lombok.extern.slf4j.Slf4j;


@Service("absenceservice")
@Slf4j
public class AbsenceService implements IAbsenceService {

	@Autowired
	private IAbsenceDao dao;

	@Override
	public Absence create(Absence absence) {
		try {
			if (absence == null) {
				return null;
			}
			return dao.save(absence);
		} catch (DataIntegrityViolationException e) {
			log.warn("MESSAGE ERREUR CREATE ABSENCE" + e.getMessage());
			return null;
		}
	}

	@Override
	public List<Absence> findAll() {
		if (dao.findAll().isEmpty()) {
			return new ArrayList<>();
		}
		return dao.findAll();

	}

	@Override
	public Absence findById(int id) {
		if (id != 0) {
			return dao.findById(id).orElse(null);
		} else {
			return null;
		}
	}

	@Override
	public boolean update(Absence absence) {
		try {
			if (dao.existsById(absence.getId())) {
				dao.save(absence);
				return true;
			} else {
				return false;
			}
		} catch (NullPointerException ec) {
			log.warn("MESSAGE ERREUR UPDATE ABSENCE" + ec.getMessage());
			return false;
		}
	}

	@Override
	public boolean deleteById(int id) {

		if (dao.findById(id).isPresent()) {
			dao.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

}
