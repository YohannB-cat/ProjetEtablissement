package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Module;

public interface IModuleService {
	
	public Module create(Module module);

	public List<Module> findAll();

	public Module findById(int id);

	public boolean update(Module module);

	public boolean deleteById(int id);


}
