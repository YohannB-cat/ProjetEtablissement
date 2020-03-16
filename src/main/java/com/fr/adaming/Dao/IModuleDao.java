package com.fr.adaming.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fr.adaming.entity.Module;

public interface IModuleDao extends JpaRepository<Module, Integer>{
	
	public Module findByNom(String nom);
	
	public void deleteByNom(String nom);
	
	

}
