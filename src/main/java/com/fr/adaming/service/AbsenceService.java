package com.fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fr.adaming.dao.IAbsenceDao;
import com.fr.adaming.entity.Absence;

public class AbsenceService implements IAbsenceService {
	
	@Autowired
	private IAbsenceDao dao;

	@Override
	public Absence create(Absence absence) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Absence> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Absence findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Absence absence) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
