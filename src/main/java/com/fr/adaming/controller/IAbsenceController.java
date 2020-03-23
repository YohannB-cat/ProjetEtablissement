package com.fr.adaming.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fr.adaming.dto.AbsenceDtoCreate;
import com.fr.adaming.dto.ResponseDto;

/**
 * Interface responsable de la couche controller de l'entité absence
 * @author Isaline
 * @since 1.0.X
 *
 */
@RequestMapping(path = "/absence")
public interface IAbsenceController {

	/**
	 * Cette méthode sert à créer et enregistrer de façon persistante une absence
	 * @param absenceDtoCreate valide (avec dates début et fin sous forme de string) 
	 * @return Renvoie un ResponseEntity avec un boolean erreur, un String message, et un objet (absence si succes).
	 */
	@PostMapping
	public ResponseEntity<ResponseDto> create(@Valid @RequestBody AbsenceDtoCreate absenceDtoCreate);

	/**
	 * Cette méthode sert à modifier une absence déjà enregistrée dans la BD
	 * @param absenceDtoCreate valide (avec dates début et fin sous forme de string) avec l'id de l'absence à modifier
	 * @return Renvoie un ResponseEntity avec un boolean erreur, un String message, et un objet null.
	 */
	@PutMapping
	public ResponseEntity<ResponseDto> update(@Valid @RequestBody AbsenceDtoCreate absenceDtoCreate);

	/**
	 * Cette méthode sert à trouver une absence enregistrée dans la BD via son Id
	 * @param id l'identifiant de l'absence que l'on cherche
	 * @return Renvoie un ResponseEntity avec un boolean erreur, un String message, et un objet (asebnce si succes).
	 */
	@GetMapping(path = "/one/{id}")
	public ResponseEntity<ResponseDto> findById(@PathVariable(name = "id") int id);

	/**
	 * Cette méthode permet de rechercher toutes les absences enregistrées dans la BD
	 * @return Renvoie un ResponseEntity avec un boolean erreur, un String message, et un objet (liste d'absences si succes).
	 */
	@GetMapping(path = "/all")
	public ResponseEntity<ResponseDto> findAll();

	/**
	 * Cette méthode permet de supprimer une absence dans la BD via son identifiant
	 * @param id l'identifiant de l'absence que l'on souhaite supprimer
	 * @return Renvoie un ResponseEntity avec un boolean erreur, un String message, et un objet null.
	 */
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<ResponseDto> delete(@PathVariable(name = "id") int id);

}
