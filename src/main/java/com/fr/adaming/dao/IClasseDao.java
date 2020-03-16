package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fr.adaming.entity.Classe;
import com.fr.adaming.entity.Niveau;

public interface IClasseDao  extends JpaRepository<Classe, Integer>{
	
	public void readByNiveau(Niveau niveau);
	
	

}
