package com.fr.adaming.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.fr.adaming.dto.EtudiantDto;
import com.fr.adaming.dto.EtudiantDtoCreate;
import com.fr.adaming.dto.ResponseDto;


/**
 * Interface responsable de la couche controller de l'entité Etudiant
 * @author clara
 * @since 1.0.x
 */
public interface IEtudiantController {

	
	/**
	 * Permet la création d'un étudiant à partir d'un EtudiantDto
	 * @param dto décrivant l'étudiant que l'on veut créer
	 * @return Renvoie un ResponseEnttity-ResponseDto avec une réponse avec un status, un boolean erreur, un String message, et un objet.
	 */
	public ResponseEntity<ResponseDto> create(@Valid @RequestBody EtudiantDto dto);

	
	/**
	 * Permet la modification d'un étudiant à partir de sa nouvelle version sous forme EtudiantDtoCreate
	 * @param dto décrivant la nouvelle version de l'étudiant, l'id doit être le même
	 * @return Renvoie un ResponseEnttity-ResponseDto avec une réponse avec un status, un boolean erreur, un String message, et un objet.
	 */
	public ResponseEntity<ResponseDto> update(@Valid @RequestBody EtudiantDtoCreate dto);

	
	/**
	 * Permet la recherche d'un étudiant à partir de son identifiant
	 * @param id entier, identifiant unique de l'étudiant que l'on veut chercher
	 * @return Renvoie un ResponseEnttity-ResponseDto avec une réponse avec un status, un boolean erreur, un String message, et un objet.
	 */
	public ResponseEntity<ResponseDto> findById(@PathVariable int id);

	
	/**
	 * Permet la lecture de tout les étudiants 
	 * @return Renvoie un ResponseEnttity-ResponseDto avec une réponse avec un status, un boolean erreur, un String message, et un objet.
	 */
	public ResponseEntity<ResponseDto> findAll();

	
	/**
	 * Permet la suppression d'un étudiant par son identitfiant
	 * @param id entier, identifiant unique de l'étudiant que l'on veut supprimer 
	 * @return Renvoie un ResponseEnttity-ResponseDto avec une réponse avec un status, un boolean erreur, un String message, et un objet.
	 */
	public ResponseEntity<ResponseDto> delete(@PathVariable int id);
}
