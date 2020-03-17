package com.fr.adaming.service;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IAbsenceDao;
import com.fr.adaming.entity.Absence;

@Service("absenceservice")
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
			e.printStackTrace();
			return null;
		}

		catch (ConstraintViolationException er) {
			er.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Absence> findAll() {
		try {
		if (dao.findAll().isEmpty()) {
			return null;
		}
		return dao.findAll();
		} catch (NullPointerException npe) {
			return null;
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
			e.printStackTrace();
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
			er.printStackTrace();
			return false;
		} catch (NullPointerException ec) {
			ec.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteById(int id) {
		try {
			if (dao.findById(id) != null && id != 0) {
				dao.deleteById(id);
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

}
