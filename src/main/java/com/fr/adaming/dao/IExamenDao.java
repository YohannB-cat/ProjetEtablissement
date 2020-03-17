package com.fr.adaming.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Examen;
@Repository
public interface IExamenDao  extends JpaRepository<Examen, Integer>{
	
	public List<Examen> listByMatiere(int id_matiere);
	
	

}
