package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Module;

/**
 * Interface service pour Module
 * 
 * @author IN-LY-004
 * @since 1.0.x
 */
public interface IModuleService {

	/**
	 * Création d'un module
	 * 
	 * @param module correspond à un objet de type module
	 * @return objet de type module
	 */
	public Module create(Module module);

	/**
	 * Retourne la liste de tous les modules
	 * 
	 * @return liste de module
	 */
	public List<Module> findAll();

	/**
	 * Retourne un module
	 * 
	 * @param id correspond à l'id du module recherché
	 * @return un objet de type module
	 */
	public Module findById(Integer id);

	/**
	 * Modifie un module
	 * 
	 * @param module correspond à un objet de type module
	 * @return booléen
	 */
	public boolean update(Module module);

	/**
	 * Supprime un module
	 * 
	 * @param id correspond l'id du module
	 * @return booléen
	 */
	public boolean deleteById(Integer id);

}
