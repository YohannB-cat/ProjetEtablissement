package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fr.adaming.entity.Etudiant;

public interface IEtudiantDao extends JpaRepository<Etudiant, Integer> {
	
	public void readByNom(String nom);
	
	public Etudiant findByNom(String nom);
	
	public Etudiant deleteByNom(String nom);

}
