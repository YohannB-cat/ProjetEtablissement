package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Classe;
import com.fr.adaming.entity.Niveau;

/**
 * Interface INiveauService reponsable de la couche service de l'entité Niveau
 * @author Flavien
 * @since 1.0.x
 *
 */
public interface INiveauService {

	/**
	 * Methode de création d'un nouveau niveau
	 * @param niveau accepte un Niveau.
	 * @return Renvoie l'entitée crée.
	 */
	public Niveau create(Niveau niveau);

	/**
	 * Methode de recherche de la liste des Niveaux
	 * @return Renvoie la liste des Niveaux.
	 */
	public List<Niveau> findAll();

	/**
	 * Methode de recherche d'un niveau par son id
	 * @param id accepte l'id du Niveau.
	 * @return Renvoie l'entitée trouvé ou null.
	 */
	public Niveau findById(int id);

	/**
	 * Methode de modification d'un niveau
	 * @param niveau accepte un Niveau à remplacer.
	 * @return Renvoie un boolean suivant si la modification a eu lieu ou pas.
	 */
	public boolean update(Niveau niveau);

	/**
	 * Methode de suppression d'un niveau par id
	 * @param id accepte l'id du Niveau à supprimer.
	 * @return Renvoie un boolean suivant si la suppression a eu lieu ou pas.
	 */
	public boolean deleteById(int id);
	
	/**
	 * Methode de recherche de la liste des classes qui dépendent du niveau dont on place l'id
	 * @param idNiveau accepte l'id du Niveau dont on veux les classes.
	 * @return Renvoie la liste des classes.
	 */
	public List<Classe> findListClasseByIdNiveau(int idNiveau);

}
