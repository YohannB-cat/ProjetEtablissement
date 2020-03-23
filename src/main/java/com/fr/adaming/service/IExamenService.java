package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Examen;

/**
 * Interface IExamenService responsable de l'entité Examne de la couche service
 * @author Yohann
 * @since 1.0.x
 *
 */
public interface IExamenService{

	/**
	 * @param exam objet Examen à enregistrer dans la DB
	 * @return L'examen créé ou null s'il n'a pas été créé
	 */
	public Examen create(Examen exam);

	/** 
	 * Méthode qui affiche la liste des examens
	 * @return La liste des examens
	 */
	public List<Examen> findAll();

	/**
	 * Affiche un examen de la DB recherché par son identifiant
	 * @param id identifiant de l'examen recherché
	 * @return L'exmane recherché
	 */
	public Examen findById(int id);

	/**
	 * Méthode qui met à jour un examen existant
	 * @param exam id de l'exam à mettre à jour
	 * @return true si la mise à jour a bien été effectuée, false sinon
	 */
	public boolean update(Examen exam);

	/**
	 * Méthode qui supprime l'examen ciblé de la DB
	 * @param id identifiant de l'examen à supprimer
	 * @return true si l'examen a bien été supprimé, false sinon
	 */
	public boolean deleteById(int id);
	
	/**
	 * Méthode qui affiche tous les examens d'une matière
	 * @param idMatiere identifiant de la matière
	 * @return La liste des examens de la matière demandée, null si la matière n'existe pas
	 */
	public List<Examen> listByMatiere(int idMatiere);

}
