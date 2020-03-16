package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fr.adaming.entity.Matiere;
import com.fr.adaming.entity.Module;

public interface IMatiereDao  extends JpaRepository<Matiere, Integer>{
	
	public void findByModule(Module module);
	
	public Matiere findByNom(String nom);
	
	public boolean deleteByNom (String nom);

}
