package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Absence;

/**
 * Interface qui contrôle la couche service de l'entité Absence
 * @author Isaline
 * @since 1.0.X
 *
 */
public interface IAbsenceService {
	
	/**
	 * description : Permet de créer une absence
	 * @param absence l'absence que l'on veut enregistrer
	 * @return l'absence que l'on veut enregistrer
	 */
	public Absence create(Absence absence);

	/**
	 * description : Permet de trouver la liste des absences
	 * @return la liste des absences enregistrées dans le système
	 */
	public List<Absence> findAll();

	/**
	 * description : Permet de recherche une absence par son identifiant
	 * @param id Un nombre entier, identifiant de l'absence à rechercher
	 * @return l'absence unique correspondant à cet identifiant, null si l'id n'est pas attribué
	 */
	public Absence findById(int id);

	/**
	 * description : Permet de modifier une absence déjà enregistrée 
	 * 
	 * @param absence la nouvelle absence qui doit remplacer l'ancienne. L'id doit être identique à l'ancien.
	 * @return true si la modification a été effectuée et false sinon
	 */
	public boolean update(Absence absence);

	/**
	 * description : Permet de supprimer une absence déjà enregistrée 
	 * @param id Un nombre entier, identifiant de l'absence à supprimer
	 * @return true si l'identifiant a été trouvé et la suppression effectuée et false sinon
	 */
	public boolean deleteById(int id);

}
