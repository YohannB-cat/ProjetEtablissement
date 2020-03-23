package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Etudiant;

/**
 * Interface responsable de la couche service de l'entité Etudiant
 * @author clara
 * @since 1.0.x
 */
public interface IEtudiantService {

	/**
	 * description : Permet de créer un étudiant
	 * @author clara
	 * @param etudiant l'étudiant que l'on veut enregistrer
	 * @return l'étudiant que l'on veut enregistrer
	 */
	public Etudiant create(Etudiant etudiant);

	
	/**
	 * description : Permet de trouver la liste des étudiants
	 * @return la liste des étudiant enregistrés dans le système
	 */
	public List<Etudiant> findAll();

	
	/**
	 * description : Permet de recherche un étudiant par son identifiant
	 * @param id Un nombre entier, identifiant de l'étudiant à rechercher
	 * @return l'étudiant unique correspondant à cet identifiant, null si l'id n'est pas attribué
	 */
	public Etudiant findById(int id);

	/**
	 * description : Permet de modifier un étudiant déjà enregistré 
	 * 
	 * @param etudiant le nouvel étudiant qui doit remplacer l'ancien. L'id doit être identique à l'ancien.
	 * @return true si la modification a été effectuée et false sinon
	 */
	public boolean update(Etudiant etudiant);

	/**
	 * description : Permet de supprimer un étudiant déjà enregistré 
	 * @param id Un nombre entier, identifiant de l'étudiant à supprimer
	 * @return true si l'identifiant a été trouvé et la suppression effectuée et false sinon
	 */
	public boolean deleteById(int id);

}
