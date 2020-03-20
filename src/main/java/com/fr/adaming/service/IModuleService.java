package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Module;

public interface IModuleService {
	
	public Module create(Module module);

	public List<Module> findAll();

	public Module findById(Integer id);

	public boolean update(Module module);

	public boolean deleteById(Integer id);


}
