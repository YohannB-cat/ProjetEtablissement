package com.fr.adaming.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
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
			if (absence == null || dao.existsById(absence.getId())) {
				return null;
			}
			return dao.save(absence);
		} catch (DataIntegrityViolationException e) {
			log.warn("MESSAGE ERREUR CREATE ABSENCE" + e.getMessage());
			return null;
		}

		catch (ConstraintViolationException er) {
			log.warn("MESSAGE ERREUR CREATE ABSENCE" + er.getMessage());
			return null;
		}
	}

	@Override
	public List<Absence> findAll() {
		try {
		if (dao.findAll().isEmpty()) {
			return new ArrayList<>();
		}
		return dao.findAll();
		} catch (NullPointerException npe) {
			log.warn("MESSAGE ERREUR FIND ALL ABSENCE" + npe.getMessage());
			return new ArrayList<>();
		}
	}

	@Override
	public Absence findById(int id) {
		try {
			if (id != 0) {
				return dao.findById(id).orElse(null);
			} else {
				return null;
			}
		} catch (InvalidDataAccessApiUsageException e) {
			log.warn("MESSAGE ERREUR FIND By ID ABSENCE" + e.getMessage());
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
		} catch (InvalidDataAccessApiUsageException er) {
			log.warn("MESSAGE ERREUR UPDATE ABSENCE" + er.getMessage());
			return false;
		} catch (NullPointerException ec) {
			log.warn("MESSAGE ERREUR UPDATE ABSENCE" + ec.getMessage());
			return false;
		}
	}

	@Override
	public boolean deleteById(int id) {
		try {
			if (dao.findById(id).isPresent()  && id != 0) {
				dao.deleteById(id);
				return true;
			} else {
				return false;
			}
		} catch (InvalidDataAccessApiUsageException e) {
			log.warn("MESSAGE ERREUR DELETE By ID ABSENCE" + e.getMessage());
			return false;
		} catch (EmptyResultDataAccessException er) {
			log.warn("MESSAGE ERREUR DELETE By ID ABSENCE" + er.getMessage());
			return false;
		}
	}

}
