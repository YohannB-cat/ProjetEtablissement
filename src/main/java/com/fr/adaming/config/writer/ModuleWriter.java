package com.fr.adaming.config.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.fr.adaming.entity.Module;
import com.fr.adaming.service.IModuleService;

/**
 * Classe writer pour module Implemente ItemWriter et redéfinit la méthode read
 * 
 * @author maxime
 *
 */
public class ModuleWriter implements ItemWriter<Module> {

	@Autowired
	private IModuleService moduleService;

	/**
	 * Méthode write qui sauvagarde des données dans la DB
	 */
	@Override
	public void write(List<? extends Module> listeModule) throws Exception {
		for (Module module : listeModule) {
			moduleService.create(module);
		}
	}

}
