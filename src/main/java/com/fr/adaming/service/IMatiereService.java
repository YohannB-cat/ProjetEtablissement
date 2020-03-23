package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Matiere;

/**
 * Interface Service pour Matière
 * 
 * @author IN-LY-004
 * @since 1.0.x
 *
 */
public interface IMatiereService {

	/**
	 * Création
	 * 
	 * @param matiere correspond à un objet de type matière
	 * @return un objet de type matière
	 */
	public Matiere create(Matiere matiere);

	/**
	 * Retourne la liste de toutes les matières
	 * 
	 * @return liste des matières
	 */
	public List<Matiere> findAll();

	/**
	 * Retourne un objet à partir d'un id
	 * 
	 * @param id correspond à l'id de la matière
	 * @return objet de type matière
	 */
	public Matiere findById(int id);

	/**
	 * Modifie une matière
	 * 
	 * @param matiere correspond à un objet de type matière
	 * @return booléen
	 */
	public Boolean update(Matiere matiere);

	/**
	 * Supprime une matière par un id
	 * 
	 * @param id correspond à l'id de la matière
	 * @return booléen
	 */
	public boolean deleteById(Integer id);

	/**
	 * Retourne la liste de toutes les matièrs d'un module
	 * 
	 * @param matieres correspond à l'id du module
	 * @return liste des matières du module
	 */
	public List<Matiere> findMatiereByIdModule(Integer matieres);

}
