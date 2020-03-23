package com.fr.adaming.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Note;

/**
 * Inteface responsable de la couche DAO pour l'entité Note
 * @author clara
 * @since 1.0.x
 */
@Repository
public interface INoteDao  extends JpaRepository<Note, Integer>{
	
	/**
	 * Permet de récupérer la liste de note pour un étudiant
	 * @param etudiant entier, identifiant unique d'un étudiant dont on veut récupérer les notes
	 * @return la liste des notes de cet étudiant, liste vide si l'etudiant n'existe pas ou si pas de notes
	 */
	@Query(value = "from Note where etudiant_id = :etudiant")
	public List<Note> listByEtudiant(@Param(value = "etudiant") int etudiant);
}
