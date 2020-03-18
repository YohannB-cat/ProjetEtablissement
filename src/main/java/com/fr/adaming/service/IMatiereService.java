package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Matiere;

public interface IMatiereService {

	public Matiere create(Matiere matiere);

	public List<Matiere> findAll();

	public Matiere findById(int id);

	public Boolean update(Matiere matiere);

	public boolean deleteById(int id);
	
	public List<Matiere> findMatiereByIdModule (Integer matieres);
	

}
