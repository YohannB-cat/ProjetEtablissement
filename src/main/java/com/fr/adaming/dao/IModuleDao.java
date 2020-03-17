package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Module;

@Repository
public interface IModuleDao extends JpaRepository<Module, Integer> {
	
	
	

}
