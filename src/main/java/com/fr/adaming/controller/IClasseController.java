package com.fr.adaming.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.fr.adaming.dto.ClasseDtoCreate;
import com.fr.adaming.dto.ResponseDto;

/**
 * Interface IClasseController reponsable de la couche controller de l'entité Classe
 * @author Flavien
 * @since 1.0.x
 *
 */
public interface IClasseController {

	/**
	 * Methode de création d'une nouvelle Classe
	 * @param dto accepte un ClasseDto sans id qui sera ensuite auto-générer.
	 * @return Renvoie un ResponseEntity-ResponseDto avec un boolean erreur, un String message, et un objet.
	 */
	public ResponseEntity<ResponseDto> create(@Valid @RequestBody ClasseDtoCreate dto);

	/**
	 * Methode de modification d'une Classe
	 * @param dto accepte un ClasseDtoCreate avec l'id de l'objet à modifié et les paramètre du nouvel objet.
	 * @return Renvoie un ResponseEntity-ResponseDto avec un boolean erreur, un String message, et un objet.
	 */
	public ResponseEntity<ResponseDto> update(@Valid @RequestBody ClasseDtoCreate dto);

	/**
	 * Methode de recherche d'une Classe par id
	 * @param id accepte l'id de l'objet à chercher. Cette id est placé dans la requète Get
	 * @return Renvoie un ResponseEntity-ResponseDto avec un boolean erreur, un String message, et un objet.
	 */
	public ResponseEntity<ResponseDto> findById(@PathVariable(name = "id") int id);

	/**
	 * Methode de recherche de la liste des Classes, n'accepte aucun param.
	 * @return Renvoie un ResponseEntity-ResponseDto avec un boolean erreur, un String message, et un objet.
	 */
	public ResponseEntity<ResponseDto> findAll();

	/**
	 * Methode de suppression d'une Classe par id
	 * @param id accepte l'id de l'objet à supprimer. Cette id est placé dans la requète Get
	 * @return Renvoie un ResponseEntity-ResponseDto avec un boolean erreur, un String message, et un objet.
	 */
	public ResponseEntity<ResponseDto> delete(@PathVariable(name = "id") int id);

}
