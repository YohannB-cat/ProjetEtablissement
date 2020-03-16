package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Module;

public interface IModuleService {
	
	public Module create(Module module);

	public List<Module> findAll();

	public Module findById(int id);

	public Module findByNom(String nom);

	public boolean update(Module module);

	public boolean deleteById(int id);
	
	public boolean deleteByNom(String nom);

}
