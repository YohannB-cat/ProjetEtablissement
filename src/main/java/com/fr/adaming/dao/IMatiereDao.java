package com.fr.adaming.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Matiere;
@Repository
public interface IMatiereDao  extends JpaRepository<Matiere, Integer>{
	
	@Query(value = "FROM Matiere WHERE module_id =:matieres")
	public List<Matiere> findMatiereByMatieres (int matieres);
	
}
