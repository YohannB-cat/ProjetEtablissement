package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Niveau;

public interface INiveauService {

	public Niveau create(Niveau niveau);

	public List<Niveau> findAll();

	public Niveau findById(int id);
	
	public Niveau findByNom(String nom);

	public boolean update(Niveau niveau);

	public boolean deleteById(int id);

}
