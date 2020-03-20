package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Examen;

public interface IExamenService{

	public Examen create(Examen exam);

	public List<Examen> findAll();

	public Examen findById(int id);

	public boolean update(Examen exam);

	public boolean deleteById(int id);
	
	public List<Examen> listByMatiere(int idMatiere);

}
