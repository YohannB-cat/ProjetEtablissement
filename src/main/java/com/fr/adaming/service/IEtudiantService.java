package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Etudiant;

public interface IEtudiantService {

	/**
	 * description : le rôle de la méthode
	 * @param etudiant description du param nommé etudiant
	 * @return object de type etudiant ou null
	 */
	public Etudiant create(Etudiant etudiant);

	public List<Etudiant> findAll();

	public Etudiant findById(int id);

	public boolean update(Etudiant etudiant);

	public boolean deleteById(int id);

}
