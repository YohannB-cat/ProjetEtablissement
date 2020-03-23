package com.fr.adaming.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.fr.adaming.dto.NiveauDto;
import com.fr.adaming.dto.NiveauDtoCreate;
import com.fr.adaming.dto.ResponseDto;

/**
 * Interface INiveauController reponsable de la couche controller de l'entité Niveau
 * @author Flavien
 * @since 1.0.x
 *
 */
public interface INiveauController {

	/**
	 * Methode de création d'un nouveau niveau
	 * @param dto accepte un NiveauDto sans id qui sera ensuite auto-générer.
	 * @return Renvoie un ResponseEntity-ResponseDto avec un boolean erreur, un String message, et un objet.
	 */
	public ResponseEntity<ResponseDto> create(@Valid @RequestBody NiveauDto dto);

	/**
	 * Methode de modification niveau
	 * @param dto accepte un NiveauDtoCreate avec l'id de l'objet à modifié et les paramètre du nouvel objet.
	 * @return Renvoie un ResponseEntity-ResponseDto avec un boolean erreur, un String message, et un objet.
	 */
	public ResponseEntity<ResponseDto> update(@Valid @RequestBody NiveauDtoCreate dto);

	/**
	 * Methode de recherche d'un Niveau par id
	 * @param id accepte l'id de l'objet à chercher. Cette id est placé dans la requète Get
	 * @return Renvoie un ResponseEntity-ResponseDto avec un boolean erreur, un String message, et un objet.
	 */
	public ResponseEntity<ResponseDto> findById(@PathVariable(name = "id",required=false) Integer id);

	/**
	 * Methode de recherche de la liste des Niveau, n'accepte aucun param.
	 * @return Renvoie un ResponseEntity-ResponseDto avec un boolean erreur, un String message, et un objet.
	 */
	public ResponseEntity<ResponseDto> findAll();

	/**
	 * Methode de suppression d'un Niveau par id
	 * @param id accepte l'id de l'objet à supprimer. Cette id est placé dans la requète Get
	 * @return Renvoie un ResponseEntity-ResponseDto avec un boolean erreur, un String message, et un objet.
	 */
	public ResponseEntity<ResponseDto> delete(@PathVariable(name = "id") int id);

	/**
	 * Methode de recherche des Classes du même Niveau, par id de ce Niveau
	 * @param id accepte l'id du Niveau dont on veut chercher les Classes. Cette id est placé dans la requète Get
	 * @return Renvoie un ResponseEntity-ResponseDto avec un boolean erreur, un String message, et un objet.
	 */
	public ResponseEntity<ResponseDto> findClasseByNiveau(@PathVariable(name = "idNiveau") int id);
}
