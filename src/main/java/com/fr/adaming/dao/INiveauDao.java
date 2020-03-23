package com.fr.adaming.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Classe;
import com.fr.adaming.entity.Niveau;

/**
 * Interface INiveauDao reponsable de la couche dao de l'entité Niveau. Contient une methodes particulières qui permet de rechercher la liste des classes qui dépendent du niveau en question.
 * @author Flavien
 * @since 1.0.x
 *
 */
@Repository
public interface INiveauDao extends JpaRepository<Niveau, Integer> {
	
	
	/**
	 * Methode de recherche des Classes du même Niveau, par id de ce Niveau
	 * @param idNiveau l'Id du niveau dont on veut les classes
	 * @return Renvoie la liste des Classes trouvé
	 */
	@Query(value = "FROM Classe WHERE id_niveau = :idNiveau")
	public List<Classe> findListClasseByIdNiveau(@Param(value = "idNiveau") int idNiveau);
	
}
