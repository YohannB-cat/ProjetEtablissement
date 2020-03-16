package com.fr.adaming.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Classe;
@Repository
public interface IClasseDao  extends JpaRepository<Classe, Integer>{
	
	public List<Classe> listByNiveau(int id_niveau);
	
	

}
