package com.fr.adaming.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Examen;
@Repository
public interface IExamenDao  extends JpaRepository<Examen, Integer>{
	
	@Query(value = "from Examen where id in (select examen from Note where module in (select x.id from Module x where id= :matiere))")
	public List<Examen> listByMatiere(@Param(value = "matiere") int matiere);
	

}
