package com.fr.adaming.controller;

import org.springframework.http.ResponseEntity;

import com.fr.adaming.dto.NoteDtoCreate;
import com.fr.adaming.dto.ResponseDto;


/**
 * Inteface responsable de la couche controller pour l'entité Note
 * @author clara
 * @since 1.0.x
 */
public interface INoteController {
	//create note
	/**
	 * Permet la création d'une note à partir d'un NoteDtoCreate
	 * @param dto décrivant la note que l'on veut créer
	 * @return Renvoie un ResponseEnttity-ResponseDto avec une réponse avec un status, un boolean erreur, un String message, et un objet.
	 */
	public ResponseEntity<ResponseDto> create(NoteDtoCreate dto);
	
	//read note by id
	/**
	 * Permet la recherche d'une note à partir de son identifiant
	 * @param id entier, idetifiant unique de la note que l'on souhaite récupérer
	 * @return Renvoie un ResponseEnttity-ResponseDto avec une réponse avec un status, un boolean erreur, un String message, et un objet.
	 */
	public ResponseEntity<ResponseDto> findById (int id);
	
	//read all
	/**
	 * Permet de récupérer toutes les notes
	 * @return Renvoie un ResponseEnttity-ResponseDto avec une réponse avec un status, un boolean erreur, un String message, et un objet.
	 */
	public ResponseEntity<ResponseDto> findAll();
	
	//update
	/**
	 * Permet la modification d'une note à partir de sa nouvelle version sous forme NoteDtoCreate
	 * @param dto décrivant la nouvelle version de la note, l'id doit être le même que l'ancienne version
	 * @return Renvoie un ResponseEnttity-ResponseDto avec une réponse avec un status, un boolean erreur, un String message, et un objet.
	 */
	public ResponseEntity<ResponseDto> update ( NoteDtoCreate dto);
	
	// delete Note by ID
	/**
	 * Permet la suppression d'une note par son identitfiant
	 * @param id entier, identifiant unique de la note que l'on veut supprimer 
	 * @return Renvoie un ResponseEnttity-ResponseDto avec une réponse avec un status, un boolean erreur, un String message, et un objet.
	 */
	public ResponseEntity<ResponseDto> deleteById (int id);
	
	// Afficher la liste des notes par etudiant
	/**
	 * Permet de trouver la liste des notes à partir de  l'identifiant d'un étudiant
	 * @param idetudiant entier, idetifiant unique de l'étudiant dont on souhaite avoir les notes
	 * @return Renvoie un ResponseEnttity-ResponseDto avec une réponse avec un status, un boolean erreur, un String message, et un objet.
	 */
	public ResponseEntity<ResponseDto> listByEtudiant(int idetudiant);

	
	
}
