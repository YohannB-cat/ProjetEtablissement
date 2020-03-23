package com.fr.adaming.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Examen;

/**
 * Interface IExamenDao étendue de la classe JpaRepository
 * @author Yohann
 * @since 1.0.x
 *
 */
@Repository
public interface IExamenDao  extends JpaRepository<Examen, Integer>{
	
	/**
	 * Méthode qui affiche la liste des examens correspondant à une matière précise
	 * @param matiere id de la matière 
	 * @return Liste d'Examen
	 */
	@Query(value = "from Examen where id in (select examen from Note where module in (select x.id from Module x where id= :matiere))")
	public List<Examen> listByMatiere(@Param(value = "matiere") int matiere);
}
