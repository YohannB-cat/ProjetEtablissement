package com.fr.adaming.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Matiere;
/**
 * <b>Interface Repository pour l'entité matière</b>
 * methode Find liste matière par id module
 * @author IN-LY-004
 * @since 1.0.x
 *
 */
@Repository
public interface IMatiereDao  extends JpaRepository<Matiere, Integer>{
	
	/**
	 * Affiche la lsite des matières correspondant à un module
	 * @param matieres correspond à l'id du module
	 * @return liste de matières (vide ou complète)
	 */
	@Query(value = "FROM Matiere WHERE module_id =:matieres")
	public List<Matiere> findMatiereByMatieres (int matieres);
	
}
