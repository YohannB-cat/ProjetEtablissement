package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Etudiant;

public interface IEtudiantService {
	
	public Etudiant create(Etudiant etudiant);

	public List<Etudiant> findAll();

	public Etudiant findById(int id);

	public Etudiant findByNom(String nom);

	public boolean update(Etudiant etudiant);

	public boolean deleteById(int id);
	
	public boolean deleteByNom(String nom);

}
