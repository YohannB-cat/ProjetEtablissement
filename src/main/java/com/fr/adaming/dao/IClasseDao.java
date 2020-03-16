package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Classe;
import com.fr.adaming.entity.Niveau;

@Repository
public interface IClasseDao  extends JpaRepository<Classe, Integer>{
	
	public void readByNiveau(Niveau niveau);
	
	

}
