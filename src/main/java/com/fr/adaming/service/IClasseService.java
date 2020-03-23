package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Classe;

/**
 * Interface IClasseService reponsable de la couche service de l'entité Classe
 * @author Flavien
 * @since 1.0.x
 *
 */
public interface IClasseService {

	/**
	 * Methode de création d'une nouvelle classe
	 * @param classe accepte un Classe.
	 * @return Renvoie l'entitée crée.
	 */
	public Classe create(Classe classe);

	/**
	 * Methode de recherche de la liste des Classes
	 * @return Renvoie la liste des Classes.
	 */
	public List<Classe> findAll();

	/**
	 * Methode de recherche d'une classe par son id
	 * @param id accepte l'id de la classe.
	 * @return Renvoie l'entitée trouvé ou null.
	 */
	public Classe findById(int id);

	/**
	 * Methode de modification d'une classe
	 * @param classe accepte une Classe à remplacer.
	 * @return Renvoie un boolean suivant si la modification a eu lieu ou pas.
	 */
	public boolean update(Classe classe);

	/**
	 * Methode de suppression d'une classe par id
	 * @param id accepte l'id de la Classe à supprimer.
	 * @return Renvoie un boolean suivant si la suppression a eu lieu ou pas.
	 */
	public boolean deleteById(int id);

}
