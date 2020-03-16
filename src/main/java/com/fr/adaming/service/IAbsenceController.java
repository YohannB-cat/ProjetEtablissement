package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Absence;

public interface IAbsenceController {
	public Absence create(Absence absence);

	public List<Absence> findAll();

	public Absence findById(int id);

	public boolean update(Absence absence);

	public boolean deleteById(int id);

}
