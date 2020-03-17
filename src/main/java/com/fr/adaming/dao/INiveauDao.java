package com.fr.adaming.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Classe;
import com.fr.adaming.entity.Niveau;

@Repository
public interface INiveauDao extends JpaRepository<Niveau, Integer> {
	
	@Query(value = "FROM Classe WHERE id = (SELECT classe_id FROM Niveau WHERE id = :idNiveau)")
	public List<Classe> listDesClasses(@Param(value = "idNiveau") int idNiveau);
	
}
