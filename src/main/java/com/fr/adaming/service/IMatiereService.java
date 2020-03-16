package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Matiere;

public interface IMatiereService {

	public Matiere create(Matiere matiere);

	public List<Matiere> findAll();

	public Matiere findById(int id);

	public Matiere findByNom(String nom);

	public boolean update(Matiere matiere);

	public boolean deleteById(int id);
	
	public boolean deleteByNom(String nom);

}
