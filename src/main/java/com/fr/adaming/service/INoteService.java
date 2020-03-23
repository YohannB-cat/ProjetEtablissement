package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Note;

/**
 * Interface responsable de la couche service de l'entité Note
 * @author clara
 * @since 1.0.x
 */
public interface INoteService {
	
	/**
	 * description : Permet de créer une note
	 * @author clara
	 * @param note que l'on veut enregistrer
	 * @return note que l'on veut enregistrer
	 */
	public Note create(Note note);

	/**
	 * description : Permet de trouver la liste des notes
	 * @return la liste des notes enregistrés dans le système
	 */
	public List<Note> findAll();

	/**
	 * description : Permet de recherche une note par son identifiant
	 * @param id Un nombre entier, identifiant de la note à rechercher
	 * @return la note unique correspondant à cet identifiant, null si l'id n'est pas attribué
	 */
	public Note findById(int id);

	/**
	 * description : Permet de modifier un étudiant déjà enregistré 
	 * 
	 * @param note le nouvel étudiant qui doit remplacer l'ancien. L'id doit être identique à l'ancien.
	 * @return true si la modification a été effectuée et false sinon
	 */
	public boolean update(Note note);

	/**
	 * description : Permet de supprimer un note déjà enregistré 
	 * @param id Un nombre entier, identifiant de la note à supprimer
	 * @return true si l'identifiant a été trouvé et la suppression effectuée et false sinon
	 */
	public boolean deleteById(int id);
	
	/**
	 * description : Permet de récupérer la liste des notes pour un étudiant donné par son identifiant.
	 * @param idetudiant Un nombre entier, identifiant de l'étudiant dont on veut connaitre les notes 
	 * @return la liste des notes de l'etudiant avec cet id, vide si pas de note ou id inconnu
	 */
	public List<Note> listByEtudiant(int idetudiant);


}
