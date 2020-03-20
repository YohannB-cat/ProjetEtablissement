package com.fr.adaming.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IModuleDao;
import com.fr.adaming.entity.Module;

import lombok.extern.slf4j.Slf4j;

@Service ("moduleservice")
@Slf4j
public class ModuleService implements IModuleService{
	
	@Autowired
	private IModuleDao dao;

	@Override
	public Module create(Module module) {
		try {
			if (module == null || dao.existsById(module.getId())) {
				return null;
			}
			return dao.save(module);
		} catch (Exception e) {
			log.error("ERROR create module"+e.getMessage());
			return null;
		}
	}

	@Override
	public List<Module> findAll() {
		List <Module> listeModule = new ArrayList<>();
		if (dao.findAll().isEmpty()) {
			return listeModule;
		}
		return dao.findAll();
	}

	@Override
	public Module findById(Integer id) {
		try {
			if (id != null) {
				return dao.findById(id).orElse(null);
			} else {
				return null;
			}
		} catch (Exception e) {
			log.error("ERROR find by id module" + e.getMessage());
			return null;
		}
	}

	@Override
	public boolean update(Module module) {
		try {
			if (dao.existsById(module.getId())) {
				dao.save(module);
				return true;
			} else {
				return false;
			}
		} catch (Exception er) {
			log.error("ERROR update module "+ er.getMessage());
			return false;
		}
	}

	@Override
	public boolean deleteById(Integer id) {

			if (dao.existsById(id)) {
				dao.deleteById(id);
				return true;
			} else {
				return false;
			}
	
	}
}
