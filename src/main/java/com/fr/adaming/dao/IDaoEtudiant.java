package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Etudiant;

@Repository
public interface IDaoEtudiant extends JpaRepository<Etudiant, Integer> {

	public Etudiant findById(int id);
	
}
