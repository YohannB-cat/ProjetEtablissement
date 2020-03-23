package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fr.adaming.entity.Etudiant;

/**
 * Inteface responsable de la couche DAO pour l'entité Etudiant
 * @author clara
 * @since 1.0.x
 */
public interface IEtudiantDao extends JpaRepository<Etudiant, Integer> {
	



}
